package com.example.pullup.controller.userDto.createUserDto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class CreateUserRequestDto (
    @Schema(example = "true", defaultValue = "false", description = "true: 선생님, false: 학생")
    val teacherCheck: Boolean,

    @NotBlank
    @Min(1)
    @Schema(example = "ash")
    val name: String,

    @NotBlank
    @Email
    @Schema(example = "ash@gmail.com")
    val email: String,

    @NotBlank
    @Schema(example = "password")
    val password: String,

    @NotBlank
    @Schema(example = "rePassword")
    val rePassword: String
)
