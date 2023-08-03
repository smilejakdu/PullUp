package com.example.pullup.controller.userDto.loginUserDto

import com.example.pullup.shared.response.CoreSuccessResponseWithData
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatusCode
import java.util.Objects

data class LoginUserResponseDto(
    @Schema(example = "true")
    override val success: Boolean = true,
    @Schema(example = "SUCCESS")
    override val message: String = "Success",
    @Schema(example = "200")
    override val statusCode: Int = HttpStatusCode.valueOf(200).value(),
    @Schema()
    override val data: Objects
): CoreSuccessResponseWithData(success, message, statusCode, data)