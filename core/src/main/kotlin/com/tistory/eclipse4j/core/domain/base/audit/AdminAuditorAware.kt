package com.tistory.eclipse4j.core.domain.base.audit

import org.springframework.context.annotation.Profile
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component(value = "adminAuditorAware")
@Profile("!default")
class AdminAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return Optional.of("dummy")
    }
}

@Component(value = "adminAuditorAware")
@Profile("default")
class AdminDefaultAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("dummy")
    }
}
