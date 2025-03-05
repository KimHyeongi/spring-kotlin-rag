package com.tistory.eclipse4j.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing(auditorAwareRef = "apiAuditorAware")
@SpringBootApplication(
    scanBasePackages = [
        "com.tistory.eclipse4j.core",
        "com.tistory.eclipse4j.app"
    ],
    exclude = [SecurityAutoConfiguration::class, OAuth2ClientAutoConfiguration::class]
)
class AppApiApplication

fun main(args: Array<String>) {
    runApplication<AppApiApplication>(*args)
}
