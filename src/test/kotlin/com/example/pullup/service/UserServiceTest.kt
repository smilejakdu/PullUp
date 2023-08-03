package com.example.pullup.service

import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*


internal class UserServiceTest {
    @Mock
    private lateinit var userRepository: IUserRepository

    @InjectMocks
    private lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
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
        // Given
        val userId = 1L
        Mockito.`when`(userRepository.findById(userId)).thenReturn(Optional.empty())

        // When
        val exception = Assertions.assertThrows(
            Exception::class.java
        ) { userService.findUserById(userId) }

        // Then
        assertEquals("User not found", exception.message)
    }
}

