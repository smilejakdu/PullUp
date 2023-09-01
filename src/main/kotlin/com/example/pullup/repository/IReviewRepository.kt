package com.example.pullup.repository

import com.example.pullup.domain.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface IReviewRepository: JpaRepository<Review, Long>
