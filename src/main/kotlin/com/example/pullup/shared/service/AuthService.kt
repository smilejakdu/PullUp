package com.example.pullup.shared.service

import com.example.pullup.domain.User
import com.example.pullup.repository.IUserRepository
import com.example.pullup.shared.exception.HttpException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val userRepository: IUserRepository
) {

    // application.properties 에 있는 JWT_SECRET 을 가져오도록 한다.
    @Value("\${JWT_SECRET}")
    private lateinit var JWT_SECRET: String

    fun createToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.email)
        val now = Date()
        val validity = Date(now.time + 3600000)  // 1 시간 유효
        val key = JWT_SECRET.toByteArray()  // Should be in a secure place

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, key)
            .compact()
    }

    fun getUserDataFromCookie(
        cookies: Array<Cookie>?
    ): User {
        var email = ""
        try {
            if (cookies == null) {
                throw HttpException(
                    ok = false,
                    httpStatus = HttpStatus.UNAUTHORIZED,
                    message = "Invalid token",
                )
            }

            for (cookie in cookies) {
                if (cookie.name == "accessToken") {
                    val accessToken = cookie.value

                    email = Jwts.parser()
                        .setSigningKey(JWT_SECRET.toByteArray())
                        .parseClaimsJws(accessToken)
                        .body
                        .subject
                }
            }
        } catch (e: Exception) {
            throw HttpException(
                ok = false,
                httpStatus = HttpStatus.UNAUTHORIZED,
                message = "Invalid token",
            )
        }
        return userRepository.findByEmail(email).orElseThrow {
            HttpException(
                ok = false,
                httpStatus = HttpStatus.NOT_FOUND,
                "User not found",
            )
        }
    }
}
