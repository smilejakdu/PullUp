package com.example.pullup.shared

import org.springframework.http.HttpStatusCode

class CoreSuccessResponseDto {
    val success: Boolean = true
    val message: String = "Success"
    val statusCode: Int = HttpStatusCode.valueOf(200).value()
}