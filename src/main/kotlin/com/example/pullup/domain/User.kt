package com.example.pullup.domain

import com.example.pullup.shared.domain.TimeEntity
import jakarta.persistence.*
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@NoArgsConstructor
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 1,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name="email", nullable = false, unique = true)
    var email: String,

    @Column(name="teacher_check", nullable = false)
    var teacherCheck: Boolean = false,

    @Column(name = "password", nullable = false)
    var password: String,
): TimeEntity()
