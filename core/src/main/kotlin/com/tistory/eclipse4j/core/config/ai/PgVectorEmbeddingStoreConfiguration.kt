package com.tistory.eclipse4j.core.config.ai

import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class PgVectorEmbeddingStoreConfiguration {
    @Value("\${langchain4j.vector.pgvector.host:localhost}")
    lateinit var pgVectorHost: String
    @Value("\${langchain4j.vector.pgvector.port:5432}")
    lateinit var pgVectorPort: String
    @Value("\${langchain4j.vector.pgvector.database:myapp}")
    lateinit var pgVectorDatabase: String
    @Value("\${langchain4j.vector.pgvector.username:admin}")
    lateinit var pgVectorUsername: String
    @Value("\${langchain4j.vector.pgvector.password:1234}")
    lateinit var pgVectorPassword: String
    @Value("\${langchain4j.vector.pgvector.table:myapp_rag.myapp_vector}")
    lateinit var pgVectorTable: String

    @Bean("pgvectorEmbeddingStore")
    fun pgvectorEmbeddingStore(): PgVectorEmbeddingStore {
        return PgVectorEmbeddingStore.builder()
            .host(pgVectorHost)
            .port(pgVectorPort.toInt())
            .database(pgVectorDatabase)
            .user(pgVectorUsername)
            .password(pgVectorPassword)
            .table(pgVectorTable)
            .dimension(1536)
            .build()
    }
}
