package com.example.pullup.shared.exception

import org.springframework.http.HttpStatus

data class HttpException(
    val ok: Boolean = false,
    val httpStatus: HttpStatus? = null,
    override val message: String? = null,
):RuntimeException(message)