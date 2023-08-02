package com.example.pullup.shared.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

data class CoreBadResponseDto(
    @Schema(example = "false")
    val ok: Boolean = false,
    @Schema(example = "400")
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    @Schema(example = "Bad Request")
    val message: String = "Bad Request"
)
