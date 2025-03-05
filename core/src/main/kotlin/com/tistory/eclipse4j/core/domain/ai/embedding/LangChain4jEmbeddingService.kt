package com.tistory.eclipse4j.core.domain.ai.embedding

import dev.langchain4j.data.embedding.Embedding
import dev.langchain4j.data.segment.TextSegment
import dev.langchain4j.model.openai.OpenAiEmbeddingModel
import org.springframework.stereotype.Service


@Service
class LangChain4jEmbeddingService(
    private val embeddingModel: OpenAiEmbeddingModel
) {
    fun generateEmbedding(text: String): Embedding {
        val segment = TextSegment.from(text)
        val response = embeddingModel.embed(segment)
        return response.content()
    }
}
