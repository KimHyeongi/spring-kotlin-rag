package com.tistory.eclipse4j.core.config.db

import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

/**
 * EnableJpaAuditing 은 각 서비스 모듈에서 처리하도록 한다.
 */
@Configuration
@EnableTransactionManagement
@EntityScan(
    basePackages = ["com.tistory.eclipse4j.core.domain"]
)
@EnableJpaRepositories(
    basePackages = ["com.tistory.eclipse4j.core.domain"],
    entityManagerFactoryRef = "mainEntityManagerFactory",
    transactionManagerRef = "mainTransactionManager",
)
class MainDatabaseConfig {

    @Value("\${spring.main-writer-datasource.hikari.ddl-auto:none}")
    lateinit var ddlAuto: String

    @Primary
    @PersistenceContext(unitName = "main")
    @Bean(name = ["mainEntityManagerFactory"])
    fun mainEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("mainDataSource") mainDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = ddlAuto
        properties["hibernate.format_sql"] = "true"
        properties["hibernate.default_schema"] = "myapp-rag"
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        properties["hibernate.physical_naming_strategy"] = "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"
        return builder
            .dataSource(mainDataSource)
            .packages("com.tistory.eclipse4j.core.domain")
            .persistenceUnit("main")
            .properties(properties)
            .build()
    }

    @Primary
    @Bean(name = ["mainTransactionManager"])
    fun mainTransactionManager(
        @Qualifier("mainEntityManagerFactory") mainEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(mainEntityManagerFactory)
    }
}
