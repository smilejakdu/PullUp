package com.example.pullup.shared.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

data class CoreInternalServerResponseDto(
    @Schema(example = "false")
    val ok: Boolean = false,
    @Schema(example = "500")
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    @Schema(example = "Internal Server Error")
    val message: String = "Internal Server Error",
)
