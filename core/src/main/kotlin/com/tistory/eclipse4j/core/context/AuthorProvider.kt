package com.tistory.eclipse4j.core.context

import org.javers.spring.auditable.AuthorProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthorProvider : AuthorProvider {
    override fun provide(): String {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        return authentication?.let {
            "dummy"
        }!!
    }
}
