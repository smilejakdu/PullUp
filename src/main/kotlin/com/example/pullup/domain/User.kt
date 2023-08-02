package com.example.pullup.domain

import com.example.pullup.shared.TimeEntity
import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name="email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,
): TimeEntity()
