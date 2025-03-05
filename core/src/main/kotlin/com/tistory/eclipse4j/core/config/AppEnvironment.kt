package com.tistory.eclipse4j.core.config

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class AppEnvironment(
    private val environment: Environment
) {

    fun isLocal(): Boolean {
        return environment.activeProfiles.any { it == "local" }
    }

    fun getCurrentProfile(): String {
        return environment.activeProfiles.first()
    }
}
