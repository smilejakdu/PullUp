package com.example.pullup.service

import com.example.pullup.controller.userDto.createUserDto.CreateUserRequestDto
import com.example.pullup.controller.userDto.createUserDto.CreateUserResponseDto
import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.services.UserService
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.service.AuthService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mindrot.jbcrypt.BCrypt
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class UserServiceTest {
    @Mock
    private lateinit var userRepository: IUserRepository

    @Mock
    private lateinit var authService: AuthService

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun findUserByIdTest() {
        // Given
        val userId = 1L
        val user = User(
            id = userId,
            name = "John Doe",
            email = "john@example.com",
            teacherCheck = false, // or true, depending on what you want to test
            password = "password"
        )
        Mockito.`when`<Optional<User>>(userRepository.findById(userId)).thenReturn(Optional.of<User>(user))

        // When
        val result = userService.findUserById(userId)

        // Then
        assertEquals(user, result)
    }

    @Test
    fun findUserByIdNotFound() {
        val nonExistentUserId = 9999L

        assertThrows(HttpException::class.java) {
            userService.findUserById(nonExistentUserId)
        }
    }

    @Test
    fun createUserTest() {
        // Given
        val user = User(
            id = 1L,
            name = "John Doe",
            email = "ash@gmail.com",
            teacherCheck = false,
            password = hashPassword("password"),
        )
        println("user: $user")
        Mockito.`when`(userRepository.save(Mockito.argThat {
            it.id == user.id
                    && it.name == user.name
                    && it.email == user.email
                    && it.teacherCheck == user.teacherCheck
        })).thenReturn(user)
        // ...        // When
        val createUserRequestDto = CreateUserRequestDto(
            teacherCheck = false, // or true, depending on what you want to test
            name = "John Doe",
            email = "ash@gmail.com",
            password = "password",
            rePassword = "password"
        )
        println("createUserRequestDto: $createUserRequestDto")
        val savedUser = userService.createUser(createUserRequestDto)
        // 테스트 메서드 내에서
        // Then
        assertEquals(CreateUserResponseDto(
            ok = true,
            message = "SUCCESS",
            statusCode = 200,
            data = user
        ), savedUser)
    }

    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }
}

