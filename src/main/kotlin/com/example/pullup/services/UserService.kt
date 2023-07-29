package com.example.pullup.services

import com.example.pullup.controller.UserDto.CreateUserRequestDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.CoreSuccessResponseDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: IUserRepository) {

    fun getUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            Exception("User not found")
        }
    }

    fun createUser(userRequestDto: CreateUserRequestDto): CoreSuccessResponseDto {
        val encoder = BCryptPasswordEncoder()
        val user = User(
            name = userRequestDto.name,
            email = userRequestDto.email,
            password = encoder.encode(userRequestDto.password)
        )

        userRepository.save(user)

        return CoreSuccessResponseDto()
    }

    fun loginUser(user: User): User {
        val encoder = BCryptPasswordEncoder()
        val userFromDb = userRepository.findByEmail(user.email)
            .orElseThrow {
                Exception("User not found")
            }

        if (encoder.matches(user.password, userFromDb.password)) {
            return userFromDb
        }

        throw Exception("User not found")
    }
}