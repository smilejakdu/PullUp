package com.example.pullup.controller.userDto.createUserDto

import io.swagger.v3.oas.annotations.media.Schema

data class CreateUserRequestDto (
    @Schema(example = "true")
    val teacherCheck: Boolean,
    @Schema(example = "String data")
    val name: String = "user name data",
    @Schema(example = "email")
    val email: String = "ash@gmail.com",
    @Schema(example = "password")
    val password: String = "1234"
)