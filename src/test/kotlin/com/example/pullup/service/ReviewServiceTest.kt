package com.example.pullup.service

import com.example.pullup.domain.User
import com.example.pullup.repository.IReviewRepository
import com.example.pullup.services.ReviewService
import com.example.pullup.shared.service.AuthService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ReviewServiceTest {
    @Mock
    private lateinit var reviewRepository: IReviewRepository

    @Mock
    private lateinit var authService: AuthService

    @InjectMocks
    private lateinit var reviewService: ReviewService

    @Test
    fun findReviewsByUserId() {
        // Given
        val userId = 1L
        val expectedUser = User(
            id = userId,
            name = "Jonh Doe",
            email = "jone@example.com",
            teacherCheck = false,
            password = "password"
        )
    }
}