package com.tistory.eclipse4j.core.config.db

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class WriteDbDatasourceConfig {
    @Primary
    @Bean(name = ["writeDataSource"])
    @ConfigurationProperties(prefix = "spring.main-writer-datasource.hikari")
    fun writeDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }
}
