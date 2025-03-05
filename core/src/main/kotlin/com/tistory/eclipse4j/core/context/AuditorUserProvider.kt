package com.tistory.eclipse4j.core.context

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@RequestScope
@Component("auditorUserProvider")
class AuditorUserProvider() {
    private var auditorUser: AuditorUser? = null
    fun setAuditorUser(auditorUser: AuditorUser) {
        this.auditorUser = auditorUser
    }

    fun hasAuditorUser(): Boolean {
        return this.auditorUser != null
    }

    fun getAuditorUserEmail(): String {
        return this.auditorUser!!.email
    }

    fun getAuditorUuid(): String {
        return this.auditorUser!!.uuid
    }
}
