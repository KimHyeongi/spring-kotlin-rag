package com.tistory.eclipse4j.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppProperty(
    val slack: Slack,
) {
    data class Slack(
        var token: String?,
        var channel: String?,
    )
}
