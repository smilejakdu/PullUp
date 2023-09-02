package com.example.pullup.services

import com.example.pullup.controller.ReviewDto.CreateReviewDto.CreateReviewRequestDto
import com.example.pullup.controller.ReviewDto.CreateReviewDto.CreateReviewResponseDto
import com.example.pullup.domain.Review
import com.example.pullup.domain.User
import com.example.pullup.repository.IReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService (
    private val reviewRepository: IReviewRepository
) {

    fun createReview(
        createReviewBody: CreateReviewRequestDto,
        user: User
    ): CreateReviewResponseDto {
        val reviewContent = createReviewBody.content;
        println(reviewContent)
        val review = Review(
            content = reviewContent,
            user = user
        )
        val createdReview = reviewRepository.save(review)
        return CreateReviewResponseDto(
            ok = true,
            message = "SUCCESS",
            statusCode = 201,
            data = createdReview
        )
    }

    fun findReviewsByUserId(userId: Int): MutableList<Review> {
    }

    fun getReviewOneById(
        id: Long
    ): Review {
        return reviewRepository.findById(id).get()
    }
}