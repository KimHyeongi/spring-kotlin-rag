package com.tistory.eclipse4j.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication(
    scanBasePackages = [
        "com.tistory.eclipse4j.core",
        "com.tistory.eclipse4j.batch"
    ],
    exclude = [SecurityAutoConfiguration::class]
)
class BatchApplication

fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args)
}
