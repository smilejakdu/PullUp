package com.example.pullup.controller

import com.example.pullup.controller.userDto.createUserDto.CreateUserRequestDto
import com.example.pullup.controller.userDto.createUserDto.CreateUserResponseDto
import com.example.pullup.controller.userDto.loginUserDto.LoginUserRequestDto
import com.example.pullup.controller.userDto.loginUserDto.UserResponseDto
import com.example.pullup.services.UserService
import com.example.pullup.shared.exception.HttpException
import com.example.pullup.shared.response.CoreBadResponseDto
import com.example.pullup.shared.response.CoreInternalServerResponseDto
import com.example.pullup.shared.response.CoreNotFoundResponseDto
import com.example.pullup.shared.response.CoreSuccessResponseWithData
import com.example.pullup.shared.service.AuthService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val authService: AuthService,
) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "SUCCESS",
            content = [Content(schema = Schema(implementation = CreateUserResponseDto::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad Request",
            content = [Content(schema = Schema(implementation = CoreBadResponseDto::class))]
        ),
        ApiResponse(responseCode = "404", description = "Not Found",
            content = [Content(schema = Schema(implementation = CoreNotFoundResponseDto::class))]
        ),
        ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = CoreInternalServerResponseDto::class))]
        )
    ])
    @PostMapping("create")
    fun createUser(
        @RequestBody body: CreateUserRequestDto
    ): ResponseEntity<CoreSuccessResponseWithData> {
        return ResponseEntity.ok(userService.createUser(body))
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "SUCCESS",
            content = [Content(schema = Schema(implementation = CoreSuccessResponseWithData::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad Request",
            content = [Content(schema = Schema(implementation = CoreBadResponseDto::class))]
        ),
        ApiResponse(responseCode = "404", description = "Not Found",
            content = [Content(schema = Schema(implementation = CoreNotFoundResponseDto::class))]
        ),
        ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = CoreInternalServerResponseDto::class))]
        )
    ])
    @PostMapping("login")
    fun loginUser(
        @RequestBody body: LoginUserRequestDto,
        response: HttpServletResponse
    ): ResponseEntity<CoreSuccessResponseWithData> {
        return ResponseEntity.ok(userService.loginUser(body, response))
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "SUCCESS",
            content = [Content(schema = Schema(implementation = CoreSuccessResponseWithData::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad Request",
            content = [Content(schema = Schema(implementation = CoreBadResponseDto::class))]
        ),
        ApiResponse(responseCode = "404", description = "Not Found",
            content = [Content(schema = Schema(implementation = CoreNotFoundResponseDto::class))]
        ),
        ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = CoreInternalServerResponseDto::class))]
        )
    ])
    @GetMapping("/my_info")
    fun myinfo(
        request: HttpServletRequest
    ): ResponseEntity<CoreSuccessResponseWithData> {
        // request 로 부터 쿠키를 가져온다.
        val cookies: Array<Cookie>? = request.cookies
        var foundUser: UserResponseDto? = null
        // cookies 값이 null 이 아닌지 체크한다.
        if (cookies != null) {
            foundUser = authService.getUserDataFromCookie(cookies)
            return ResponseEntity.ok(CoreSuccessResponseWithData(data = foundUser))
        } else {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.BAD_REQUEST,
                message = "Cookie is null"
            )
        }
    }

    @GetMapping("/{userId}")
    fun getOneUser(
        @PathVariable userId: Long
    ): ResponseEntity<CoreSuccessResponseWithData> {
        return ResponseEntity.ok(userService.findUserById(userId))
    }
}