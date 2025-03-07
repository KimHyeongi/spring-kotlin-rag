package com.tistory.eclipse4j.core.config.ai

import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class PgVectorEmbeddingStoreConfiguration {
    @Value("\${langchain4j.vector.pgvector.table:myapp_rag.myapp_vector}")
    lateinit var pgVectorTable: String

    @Bean("pgvectorEmbeddingStore")
    fun pgvectorEmbeddingStore(dataSource: DataSource): PgVectorEmbeddingStore {
        return PgVectorEmbeddingStore.datasourceBuilder()
            .datasource(dataSource)
            .table(pgVectorTable)
            .dimension(1536)
            .createTable(false)
            //.useIndex(true)
            //.indexListSize(100)
            .build()
    }

}
