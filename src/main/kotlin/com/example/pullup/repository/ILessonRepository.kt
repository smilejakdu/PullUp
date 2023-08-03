package com.example.pullup.repository

import com.example.pullup.domain.Lesson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ILessonRepository: JpaRepository<Lesson, Long>{

}