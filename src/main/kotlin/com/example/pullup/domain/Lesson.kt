package com.example.pullup.domain

import com.example.pullup.shared.TimeEntity
import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@NoArgsConstructor
@Table(name = "lessons")
data class Lesson(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "price", nullable = false)
    val price: String,

    @Column(name="user_id", nullable = false)
    val userId: Int
):TimeEntity()