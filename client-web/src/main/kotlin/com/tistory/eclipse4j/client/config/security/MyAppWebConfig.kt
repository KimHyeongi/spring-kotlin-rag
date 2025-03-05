package com.tistory.eclipse4j.client.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MyAppWebConfig() : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(auditorInterceptor)
//            .addPathPatterns("/**")
    }
}
