package com.example.pullup.domain

import com.example.pullup.shared.Enum.SkillSetEnum
import com.example.pullup.shared.domain.TimeEntity
import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "lessons")
data class Lesson(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "skill_set", nullable = false)
    @Enumerated(EnumType.STRING)
    val skillSetEnum: SkillSetEnum,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "price", nullable = false)
    val price: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
): TimeEntity()
