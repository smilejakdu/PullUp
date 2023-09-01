package com.example.pullup.controller.ReviewDto.CreateReviewDto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class CreateReviewRequestDto(
    @NotBlank
    @Min(1)
    @Schema(example = "review content")
    val content: String
)