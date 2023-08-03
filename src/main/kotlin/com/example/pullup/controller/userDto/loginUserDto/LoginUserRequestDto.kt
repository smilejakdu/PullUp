package com.example.pullup.controller.userDto.loginUserDto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginUserRequestDto(
    @Schema(example = "true")
    val teacherCheck: Boolean,
    @Schema(example = "ash@gmail.com")
    val email: String,
    @Schema(example = "password")
    val password: String
)