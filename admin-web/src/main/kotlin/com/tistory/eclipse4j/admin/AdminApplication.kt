package com.tistory.eclipse4j.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@EnableJpaAuditing(auditorAwareRef = "adminAuditorAware")
@SpringBootApplication(
    scanBasePackages = [
        "com.tistory.eclipse4j.core",
        "com.tistory.eclipse4j.admin"
    ]
)
@EnableRedisHttpSession
class AdminApplication

fun main(args: Array<String>) {
    runApplication<AdminApplication>(*args)
}
