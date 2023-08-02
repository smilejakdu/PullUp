package com.example.pullup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing // 생성일자와 수정일짜
@SpringBootApplication
class PullUpApplication

fun main(args: Array<String>) {
    runApplication<PullUpApplication>(*args)
}
