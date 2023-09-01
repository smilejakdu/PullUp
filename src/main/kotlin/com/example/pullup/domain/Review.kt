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
@Table(name = "reviews")
data class Review (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Column(name = "content", nullable = false)
    val content: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
): TimeEntity()
