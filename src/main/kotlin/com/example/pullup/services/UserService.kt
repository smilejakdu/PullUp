package com.example.pullup.services

import com.example.pullup.controller.userDto.createUserDto.CreateUserRequestDto
import com.example.pullup.controller.userDto.createUserDto.CreateUserResponseDto
import com.example.pullup.controller.userDto.loginUserDto.LoginUserRequestDto
import com.example.pullup.controller.userDto.loginUserDto.LoginUserResponseDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.*

@Service
class UserService(
    private val userRepository: IUserRepository
) {

    // application.properties 에 있는 JWT_SECRET 을 가져오도록 한다.
    @Value("\${JWT_SECRET}")
    private lateinit var JWT_SECRET: String

    fun findUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            HttpException(
                ok = false,
                httpStatus = HttpStatus.NOT_FOUND,
                message = "User not found"
            )}
    }

    fun createUser(userRequestDto: CreateUserRequestDto): CreateUserResponseDto {
        val email = userRequestDto.email;
        val name = userRequestDto.name;
        val teacherCheck = userRequestDto.teacherCheck;
        val password = userRequestDto.password;
        val rePassword = userRequestDto.rePassword;

        if (userRepository.findByEmail(email).isPresent) {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.BAD_REQUEST,
                message = "User with this email already exists"
            )
        }

        if (password != rePassword) {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.BAD_REQUEST,
                message = "Passwords do not match"
            )
        }

        val user = User(
            teacherCheck = teacherCheck,
            name = name,
            email = email,
            password = hashPassword(password)
        )

        val savedUser = userRepository.save(user)
        return CreateUserResponseDto(
            ok = true,
            message = "SUCCESS",
            statusCode = HttpStatus.valueOf(200).value(),
            data = savedUser
        )
    }

    fun loginUser(user: LoginUserRequestDto): CoreSuccessResponseWithData {
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

            val token = createToken(userFromDb)
            val userResponseDto = LoginUserResponseDto(
                id = userFromDb.id,
                name = userFromDb.name,
                email = userFromDb.email,
                teacherCheck = userFromDb.teacherCheck,
                token = token
            )

            return CoreSuccessResponseWithData(
                ok = true,
                message = "SUCCESS",
                statusCode = HttpStatus.valueOf(200).value(),
                data = userResponseDto
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

    fun createToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.email)
        val now = Date()
        val validity = Date(now.time + 3600000)  // 1 시간 유효
        val key = JWT_SECRET.toByteArray()  // Should be in a secure place

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, key)
            .compact()
    }
}