package com.tistory.eclipse4j.core.config.db

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class ReadDbDatasourceConfig {
    @Bean(name = ["readDataSource"])
    @ConfigurationProperties(prefix = "spring.main-reader-datasource.hikari")
    fun readDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }
}
