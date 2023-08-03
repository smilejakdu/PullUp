package com.example.pullup.controller.UserDto

data class CreateUserRequestDto (
    val teacherCheck: Boolean,
    val name: String,
    val email: String,
    val password: String
)