package com.example.pullup.controller

import com.example.pullup.services.LessonService
import com.example.pullup.shared.response.CoreBadResponseDto
import com.example.pullup.shared.response.CoreInternalServerResponseDto
import com.example.pullup.shared.response.CoreNotFoundResponseDto
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lessons")
class LessonController(
    val lessonService: LessonService
) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "SUCCESS",
            content = [Content(schema = Schema(implementation = CoreSuccessResponseWithData::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad Request",
            content = [Content(schema = Schema(implementation = CoreBadResponseDto::class))]
        ),
        ApiResponse(responseCode = "404", description = "Not Found",
            content = [Content(schema = Schema(implementation = CoreNotFoundResponseDto::class))]
        ),
        ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = CoreInternalServerResponseDto::class))]
        )
    ])
    @GetMapping()
    fun getLessonList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Any {
        val pageable: PageRequest = PageRequest.of(page, size)
        val responseLessonData =  ResponseEntity.ok(lessonService.getLessonList(pageable))
        return if (responseLessonData.statusCode.is2xxSuccessful)
            CoreSuccessResponseWithData(data = responseLessonData.body)
        else
            CoreBadResponseDto()
    }
}