package com.example.pullup.services

import com.example.pullup.controller.UserDto.CreateUserRequestDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.CoreSuccessResponseDto
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: IUserRepository) {

    fun getUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            Exception("User not found")
        }
    }

    fun createUser(userRequestDto: CreateUserRequestDto): CoreSuccessResponseDto {
        val user = User(
            name = userRequestDto.name,
            email = userRequestDto.email,
            password = hashPassword(userRequestDto.password)
        )

        userRepository.save(user)

        return CoreSuccessResponseDto()
    }

    fun loginUser(user: User): User {
        val userFromDb = userRepository.findByEmail(user.email)
            .orElseThrow { Exception("User not found : ${user.email}") }

        return if (checkPassword(user.password, userFromDb.password)) {
            userFromDb
        } else {
            throw Exception("Password is not correct")
        }
    }

    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}