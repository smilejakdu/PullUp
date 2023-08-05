package com.example.pullup.controller.userDto.loginUserDto

data class LoginUserResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val teacherCheck: Boolean,
    val accessToken: String
)