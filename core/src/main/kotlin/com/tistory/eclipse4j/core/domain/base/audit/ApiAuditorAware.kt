package com.tistory.eclipse4j.core.domain.base.audit

import com.tistory.eclipse4j.core.context.ApplicationContextProvider
import com.tistory.eclipse4j.core.context.AuditorUserProvider
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component(value = "apiAuditorAware")
@Profile("!default")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ApiAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val auditorUserProvider = ApplicationContextProvider
            .getContext()
            .getBean("auditorUserProvider") as AuditorUserProvider
        return Optional.of(auditorUserProvider.getAuditorUuid())
    }
}

@Component(value = "apiAuditorAware")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Profile("default")
class ApiDefaultAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("dummy")
    }
}
