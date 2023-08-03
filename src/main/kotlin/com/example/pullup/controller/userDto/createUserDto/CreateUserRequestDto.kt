package com.example.pullup.controller.userDto.createUserDto

import io.swagger.v3.oas.annotations.media.Schema

data class CreateUserRequestDto (
    @Schema(example = "true")
    val teacherCheck: Boolean,
    @Schema(example = "ash")
    val name: String,
    @Schema(example = "ash@gmail.com")
    val email: String,
    @Schema(example = "password")
    val password: String
)
