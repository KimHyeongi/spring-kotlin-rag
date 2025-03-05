package com.tistory.eclipse4j.core.config.db

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
class RoutingDataSourceConfig {
    @Bean(name = ["mainRoutingDataSource"])
    fun routingDataSource(
        @Qualifier("writeDataSource") writeDataSource: DataSource,
        @Qualifier("readDataSource") readDataSource: DataSource
    ): DataSource {
        val routingDataSource = RWRoutingDataSource()
        val dataSourceMap: MutableMap<Any, Any> = HashMap()
        dataSourceMap[DataSourceType.WRITE] = writeDataSource
        dataSourceMap[DataSourceType.READ] = readDataSource
        routingDataSource.setTargetDataSources(dataSourceMap)
        routingDataSource.setDefaultTargetDataSource(writeDataSource)
        return routingDataSource
    }

    @Bean(name = ["mainDataSource"])
    fun mainDataSource(@Qualifier("mainRoutingDataSource") routingDataSource: DataSource): DataSource {
        return LazyConnectionDataSourceProxy(routingDataSource)
    }
}
