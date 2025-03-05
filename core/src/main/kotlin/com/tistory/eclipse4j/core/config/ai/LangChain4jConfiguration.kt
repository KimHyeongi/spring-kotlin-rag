package com.tistory.eclipse4j.core.config.ai

import com.tistory.eclipse4j.core.domain.ai.service.ContentsSummaryAgentService
import com.tistory.eclipse4j.core.domain.ai.service.CounselorAgentService
import com.tistory.eclipse4j.core.domain.ai.service.ProfanityAgentService
import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.service.AiServices
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LangChain4jConfiguration {

    @Bean
    fun summaryAgent(chatLanguageModel: ChatLanguageModel): ContentsSummaryAgentService {
        return AiServices.builder(ContentsSummaryAgentService::class.java)
            .chatLanguageModel(chatLanguageModel)
            .build()
    }
    @Bean
    fun profanityAgent(chatLanguageModel: ChatLanguageModel): ProfanityAgentService {
        return AiServices.builder(ProfanityAgentService::class.java)
            .chatLanguageModel(chatLanguageModel)
            .build()
    }
    @Bean
    fun counselorAgent(chatLanguageModel: ChatLanguageModel): CounselorAgentService {
        return AiServices.builder(CounselorAgentService::class.java)
            .chatLanguageModel(chatLanguageModel)
            .build()
    }
}
