package com.example.pullup.controller.UserDto

data class LoginUserRequestDto(
    val teacherCheck: Boolean,
    val email: String,
    val password: String
)