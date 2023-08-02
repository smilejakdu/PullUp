package com.example.pullup.shared.exception

import org.springframework.http.HttpStatus

open class HttpException(
    var ok: Boolean = false,
    var httpStatus: HttpStatus? = null,
    override var message: String? = null,
) : Exception(message)