package com.example.pullup.controller.userDto.loginUserDto

data class LoginUserRequestDto(
    val teacherCheck: Boolean,
    val email: String,
    val password: String
)