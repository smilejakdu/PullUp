package com.example.pullup.shared.response

import org.springframework.http.HttpStatusCode

data class CoreSuccessResponseWithData(
    val success: Boolean = true,
    val message: String = "Success",
    val statusCode: Int = HttpStatusCode.valueOf(200).value(),
    val data: Any? = null
)