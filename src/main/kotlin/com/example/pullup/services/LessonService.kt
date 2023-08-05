package com.example.pullup.services

import com.example.pullup.domain.Lesson
import com.example.pullup.repository.ILessonRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LessonService(
    private val lessonRepository: ILessonRepository
){
    fun getLessonList(
        pageable: PageRequest
    ): Page<Lesson> {
        return lessonRepository.findAll(pageable)
    }
}