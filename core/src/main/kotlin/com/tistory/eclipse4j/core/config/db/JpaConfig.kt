package com.tistory.eclipse4j.core.config.db

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * EnableJpaAuditing 은 각 서비스 모듈에서 처리하도록 한다.
 */
@Configuration
@EnableJpaAuditing
class JpaConfig()
