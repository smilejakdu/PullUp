package com.example.pullup.controller

import com.example.pullup.controller.UserDto.CreateUserRequestDto
import com.example.pullup.domain.User
import com.example.pullup.services.UserService
import com.example.pullup.shared.CoreSuccessResponseDto
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
}