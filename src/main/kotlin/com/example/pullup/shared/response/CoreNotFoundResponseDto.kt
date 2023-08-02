package com.example.pullup.shared.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

data class CoreNotFoundResponseDto(
    @Schema(example = "false")
    val ok: Boolean = false,
    @Schema(example = "404")
    val httpStatus: HttpStatus = HttpStatus.NOT_FOUND,
    @Schema(example = "Not Found")
    val message: String = "Not Found"
)