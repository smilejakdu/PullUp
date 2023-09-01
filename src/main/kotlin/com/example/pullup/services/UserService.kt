package com.example.pullup.services

import com.example.pullup.controller.userDto.createUserDto.CreateUserRequestDto
import com.example.pullup.controller.userDto.createUserDto.CreateUserResponseDto
import com.example.pullup.controller.userDto.loginUserDto.LoginUserRequestDto
import com.example.pullup.controller.userDto.loginUserDto.LoginUserResponseDto
import com.example.pullup.controller.userDto.loginUserDto.UserResponseDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import com.example.pullup.shared.service.AuthService
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse

@Service
class UserService(
    private val userRepository: IUserRepository,
    private val authService: AuthService,
) {

    fun findUserById(id: Long): User? {
        return userRepository.findById(id).orElseThrow {
            HttpException(
                ok = false,
                httpStatus = HttpStatus.NOT_FOUND,
                message = "User not found"
            )
        }
    }

    private fun checkExistingEmail(email:String) {
        if (userRepository.findByEmail(email).isPresent) {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.BAD_REQUEST,
                message = "User with this email already exists"
            )
        }
    }

    private fun validatePassword(password:String, rePassword:String) {
        if (password != rePassword) {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.BAD_REQUEST,
                message = "Passwords do not match"
            )
        }
    }

    fun createUser(userRequestDto: CreateUserRequestDto): CreateUserResponseDto {
        checkExistingEmail(userRequestDto.email)
        // 기존에 가입한 이메일이 있는지 확인한다.
        validatePassword(userRequestDto.password, userRequestDto.rePassword)

        val user = User(
            teacherCheck = userRequestDto.teacherCheck,
            name = userRequestDto.name,
            email = userRequestDto.email,
            password = hashPassword(userRequestDto.password)
        )

        val savedUser = userRepository.save(user)
        return CreateUserResponseDto(
            ok = true,
            message = "SUCCESS",
            statusCode = HttpStatus.valueOf(200).value(),
            data = savedUser
        )
    }

    fun loginUser(
        user: LoginUserRequestDto,
        response: HttpServletResponse
    ): CoreSuccessResponseWithData {
        try {
            val userFromDb = userRepository.findByEmail(user.email)
                .orElseThrow { HttpException(
                    ok = false,
                    httpStatus = HttpStatus.NOT_FOUND,
                    message = "User not found"
                ) }

            if (!checkPassword(user.password, userFromDb.password)) {
                throw HttpException(
                    ok = false,
                    httpStatus = HttpStatus.BAD_REQUEST,
                    message = "Password is not correct"
                )
            }

            val token = authService.createToken(userFromDb)
            val userResponseDto = UserResponseDto(
                id = userFromDb.id,
                name = userFromDb.name,
                email = userFromDb.email,
                teacherCheck = userFromDb.teacherCheck,
            )
            val loginUserResponseDto = LoginUserResponseDto(
                user = userResponseDto,
                accessToken = token
            )

            // Create a new cookie with the JWT token
            val cookie = Cookie("accessToken", token)
            cookie.maxAge = 60 * 60 * 24
            cookie.isHttpOnly = true
            // Adding cookie to response
            response.addCookie(cookie)

            return CoreSuccessResponseWithData(
                ok = true,
                message = "SUCCESS",
                statusCode = HttpStatus.valueOf(200).value(),
                data = loginUserResponseDto
            )
        } catch (e: Exception) {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
                message = "서버 에러",
            )
        }
    }

    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}