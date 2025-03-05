package com.tistory.eclipse4j.client.config.security

import com.tistory.eclipse4j.client.config.filter.MyAppXssFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyAppXssFilterConfig {
    @Bean
    fun myAppXssFilterRegistrationBean(): FilterRegistrationBean<MyAppXssFilter> {
        val registrationBean = FilterRegistrationBean<MyAppXssFilter>()
        registrationBean.filter = MyAppXssFilter()
        registrationBean.order = 1
        registrationBean.addUrlPatterns("/*")
        registrationBean.isEnabled = false
        return registrationBean
    }
}
