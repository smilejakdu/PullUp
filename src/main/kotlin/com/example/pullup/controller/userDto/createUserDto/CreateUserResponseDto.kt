package com.example.pullup.controller.userDto.createUserDto

import com.example.pullup.domain.User
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatusCode

data class CreateUserResponseDto(
    @Schema(example = "true")
    override val ok: Boolean = true,
    @Schema(example = "SUCCESS")
    override val message: String = "SUCCESS",
    @Schema(example = "200")
    override val statusCode: Int = HttpStatusCode.valueOf(200).value(),
    @Schema()
    override val data: User? = null
) : CoreSuccessResponseWithData(ok, message, statusCode, data)