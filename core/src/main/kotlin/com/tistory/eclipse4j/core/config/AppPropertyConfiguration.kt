package com.tistory.eclipse4j.core.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [AppProperty::class])
class AppPropertyConfiguration
