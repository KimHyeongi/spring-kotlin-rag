package com.tistory.eclipse4j.app.config.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.MDC
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class LogInterceptor : HandlerInterceptor {

    private val log = KotlinLogging.logger { }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        if (HttpMethod.OPTIONS.name() != request.method) {
            log.debug { "[${request.method}] ${request.requestURI}" }
        }
        MDC.clear()
    }
}
