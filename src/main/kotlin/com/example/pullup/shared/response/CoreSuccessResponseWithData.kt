package com.example.pullup.shared.response

import org.springframework.http.HttpStatusCode

open class CoreSuccessResponseWithData(
    open val ok: Boolean = true,
    open val message: String = "Success",
    open val statusCode: Int = HttpStatusCode.valueOf(200).value(),
    open val data: Any? = null
)