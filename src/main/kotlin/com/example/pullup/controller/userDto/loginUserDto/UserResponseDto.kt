package com.example.pullup.controller.userDto.loginUserDto

data class UserResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val teacherCheck: Boolean,
)