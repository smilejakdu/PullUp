package com.example.pullup.controller.userDto.loginUserDto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginUserResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val teacherCheck: Boolean,
)