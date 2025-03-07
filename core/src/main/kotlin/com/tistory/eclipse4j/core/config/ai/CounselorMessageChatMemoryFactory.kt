package com.tistory.eclipse4j.core.config.ai

import dev.langchain4j.memory.ChatMemory
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import org.springframework.stereotype.Component


@Component
class CounselorMessageChatMemoryFactory(
    private val counselorRedisChatMemoryStore: CounselorRedisChatMemoryStore
) {
    fun createCounselorChatMemory(sessionId: String, maxMessages: Int = 20): ChatMemory {
        return MessageWindowChatMemory.builder()
            .id(sessionId)
            .chatMemoryStore(counselorRedisChatMemoryStore)
            .maxMessages(maxMessages)
            .build()
    }
}
