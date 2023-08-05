package com.example.pullup.domain

import com.example.pullup.shared.TimeEntity
import jakarta.persistence.*
import lombok.NoArgsConstructor

@Entity
@NoArgsConstructor
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name="email", nullable = false, unique = true)
    val email: String,

    @Column(name="teacher_check", nullable = false, unique = true)
    val teacherCheck: Boolean = false,

    @Column(name = "password", nullable = false)
    val password: String,
): TimeEntity()
