package com.example.pullup.shared.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(HttpException::class)
    fun handleHttpException(e: HttpException): ResponseEntity<Any> {
        return ResponseEntity.status(e.httpStatus ?: HttpStatus.INTERNAL_SERVER_ERROR)
            .body(mapOf(
                "ok" to e.ok,
                "statusCode" to e.httpStatus?.value(),
                "message" to e.message
            ))
    }
}
