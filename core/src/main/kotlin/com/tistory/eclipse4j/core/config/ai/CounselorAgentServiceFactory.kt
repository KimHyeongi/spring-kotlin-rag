package com.tistory.eclipse4j.core.config.ai

import com.tistory.eclipse4j.core.domain.ai.service.CounselorAgentService
import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.service.AiServices
import org.springframework.stereotype.Component

@Component
class CounselorAgentServiceFactory(
    private val chatLanguageModel: ChatLanguageModel,
    private val chatMemoryFactory: CounselorMessageChatMemoryFactory
) {
    fun createCounselorAgent(sessionId: String): CounselorAgentService {
        val chatMemory = chatMemoryFactory.createCounselorChatMemory(sessionId)
        return AiServices.builder(CounselorAgentService::class.java)
            .chatLanguageModel(chatLanguageModel)
            .chatMemory(chatMemory)
            .build()
    }
}
