package com.example.pullup.shared

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@OpenAPIDefinition(
    info = Info(title = "Pull UP API 명세",
        description = "Pull UP API 명세",
        version = "v1")
)
@Configuration
class SwaggerConfig {
    @Bean
    fun api(): GroupedOpenApi {
        val paths = arrayOf("/api/**")

        return GroupedOpenApi.builder()
            .group("PULL UP API")
            .pathsToMatch(*paths)
            .build()
    }
}
