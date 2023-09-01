package com.example.pullup.domain

import com.example.pullup.shared.domain.TimeEntity
import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Setter
@Getter
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

    @OneToMany(mappedBy = "user",)
    var lessons: MutableList<Lesson> = mutableListOf(),
): TimeEntity()
