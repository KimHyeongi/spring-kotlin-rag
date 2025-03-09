package com.tistory.eclipse4j.core.config.ai

import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.model.ollama.OllamaEmbeddingModel
import dev.langchain4j.model.openai.OpenAiChatModel
import dev.langchain4j.model.openai.OpenAiEmbeddingModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class LlmConfig {

    // ChatLanguageModel 빈 설정
    @Bean
    @ConditionalOnProperty(name = ["langchain4j.provider"], havingValue = "openai")
    fun openAiChatModel(
        @Value("\${langchain4j.open-ai.chat-model.api-key}") apiKey: String,
        @Value("\${langchain4j.open-ai.chat-model.model-name}") model: String,
    ): ChatLanguageModel {
        return OpenAiChatModel.builder()
            .apiKey(apiKey)
            .modelName(model)
            .build()
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = ["langchain4j.provider"], havingValue = "ollama")
    fun ollamaChatModel(
        @Value("\${langchain4j.ollama.chat-model.base-url}") baseUrl: String,
        @Value("\${langchain4j.ollama.chat-model.model-name}") model: String,
        @Value("\${langchain4j.ollama.chat-model.temperature:0.7}") temperature: Double
    ): ChatLanguageModel {
        return OllamaChatModel.builder()
            .baseUrl(baseUrl)
            .modelName(model)
            .temperature(temperature)
            .build()
    }

    // EmbeddingModel 빈 설정
    @Bean
    @ConditionalOnProperty(name = ["langchain4j.provider"], havingValue = "openai")
    fun openAiEmbeddingModel(
        @Value("\${langchain4j.open-ai.embedding-model.api-key}") apiKey: String,
        @Value("\${langchain4j.open-ai.embedding-model.model-name:text-embedding-3-small}") model: String
    ): EmbeddingModel {
        return OpenAiEmbeddingModel.builder()
            .apiKey(apiKey)
            .dimensions(1536)
            .modelName(model)
            .build()
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = ["langchain4j.provider"], havingValue = "ollama")
    fun ollamaEmbeddingModel(
        @Value("\${langchain4j.ollama.embedding-model.base-url}") baseUrl: String,
        @Value("\${langchain4j.ollama.embedding-model.model-name}") model: String
    ): EmbeddingModel {
        return OllamaEmbeddingModel.builder()
            .baseUrl(baseUrl)
            .modelName(model)
            .build()
    }
}
