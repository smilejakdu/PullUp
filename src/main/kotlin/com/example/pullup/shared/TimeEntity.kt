package com.example.pullup.shared

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class TimeEntity {
    @CreatedDate
    @Column(updatable = false)
    val createdAt: LocalDateTime? = null

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null

    val deletedAt: LocalDateTime? = null
}