package com.example.pullup.services

import com.example.pullup.controller.userDto.createUserDto.CreateUserRequestDto
import com.example.pullup.controller.userDto.createUserDto.CreateUserResponseDto
import com.example.pullup.controller.userDto.loginUserDto.LoginUserRequestDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: IUserRepository) {

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
        val password = userRequestDto.password;
        val rePassword = userRequestDto.rePassword;

        if (userRepository.findByEmail(userRequestDto.email).isPresent) {
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

    fun loginUser(user: LoginUserRequestDto): CoreSuccessResponseWithData {
        val userFromDb = userRepository.findByEmail(user.email)
            .orElseThrow { HttpException(
                ok = false,
                httpStatus = HttpStatus.NOT_FOUND,
                message = "User not found"
            ) }

        return if (checkPassword(user.password, userFromDb.password)) {
            CoreSuccessResponseWithData(data = userFromDb)
        } else {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.BAD_REQUEST,
                "Password is not correct"
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