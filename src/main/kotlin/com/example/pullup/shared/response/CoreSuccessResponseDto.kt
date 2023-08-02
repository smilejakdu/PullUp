package com.example.pullup.shared.response

import org.springframework.http.HttpStatusCode

data class CoreSuccessResponseDto(
    val success: Boolean = true,
    val message: String = "Success",
    val statusCode: Int = HttpStatusCode.valueOf(200).value()
)