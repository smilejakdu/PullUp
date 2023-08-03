package com.example.pullup.services

import com.example.pullup.controller.UserDto.CreateUserRequestDto
import com.example.pullup.controller.UserDto.LoginUserRequestDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.response.CoreSuccessResponseDto
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: IUserRepository) {

    fun findUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            Exception("User not found")
        }
    }

    fun createUser(userRequestDto: CreateUserRequestDto): CoreSuccessResponseDto {
        val user = User(
            teacherCheck = userRequestDto.teacherCheck,
            name = userRequestDto.name,
            email = userRequestDto.email,
            password = hashPassword(userRequestDto.password)
        )

        userRepository.save(user)
        return CoreSuccessResponseDto()
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
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}