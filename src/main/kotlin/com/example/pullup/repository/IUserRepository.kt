package com.example.pullup.repository

import com.example.pullup.domain.User

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IUserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.reviews WHERE u.id = :userId")
    fun findUserWithReviewsById(userId: Long): Optional<User>
}
