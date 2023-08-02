package com.example.pullup.controller

import com.example.pullup.controller.UserDto.CreateUserRequestDto
import com.example.pullup.controller.UserDto.LoginUserRequestDto
import com.example.pullup.domain.User
import com.example.pullup.services.UserService
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.response.*
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
class UserController(
    val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        return ResponseEntity.ok(userService.getUserById(id))
    }

    @PostMapping("create")
    fun createUser(
        @RequestBody body: CreateUserRequestDto
    ): ResponseEntity<CoreSuccessResponseDto> {
        return ResponseEntity.ok(userService.createUser(body))
    }

    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "SUCCESS",
            content = [Content(schema = Schema(implementation = CoreSuccessResponseWithData::class))]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = CoreBadResponseDto::class))]
        ),
        ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [Content(schema = Schema(implementation = CoreNotFoundResponseDto::class))]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = CoreInternalServerResponseDto::class))]
        )
    ])
    @PostMapping("login")
    fun loginUser(@RequestBody body: LoginUserRequestDto): ResponseEntity<CoreSuccessResponseWithData> {
        return ResponseEntity.ok(userService.loginUser(body))
    }
}