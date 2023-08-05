package com.example.pullup.controller.userDto.loginUserDto

data class LoginUserResponseDto(
   val user: UserResponseDto,
   val accessToken: String
)