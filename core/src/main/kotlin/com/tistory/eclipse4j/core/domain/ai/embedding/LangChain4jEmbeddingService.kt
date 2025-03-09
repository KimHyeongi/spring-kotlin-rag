package com.tistory.eclipse4j.core.domain.ai.embedding

import dev.langchain4j.data.embedding.Embedding
import dev.langchain4j.data.segment.TextSegment
import dev.langchain4j.model.embedding.EmbeddingModel
import org.springframework.stereotype.Service


@Service
class LangChain4jEmbeddingService(
    private val embeddingModel: EmbeddingModel
) {
    fun generateEmbedding(text: String): Embedding {
        val segment = TextSegment.from(text)
        val response = embeddingModel.embed(segment)
        return response.content()
    }
}
