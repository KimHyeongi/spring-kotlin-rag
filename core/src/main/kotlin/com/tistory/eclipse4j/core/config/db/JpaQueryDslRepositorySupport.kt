package com.tistory.eclipse4j.core.config.db

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
abstract class JpaQueryDslRepositorySupport(domainClass: Class<*>) :
    QuerydslRepositorySupport(domainClass) {

    @Override
    @PersistenceContext(unitName = "main")
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }

    @Override
    override fun getEntityManager(): EntityManager {
        return super.getEntityManager() ?: throw IllegalStateException("EntityManager is not set")
    }

    @Override
    override fun getQuerydsl(): Querydsl {
        return super.getQuerydsl() ?: throw IllegalStateException("Querydsl is not initialized")
    }
}
