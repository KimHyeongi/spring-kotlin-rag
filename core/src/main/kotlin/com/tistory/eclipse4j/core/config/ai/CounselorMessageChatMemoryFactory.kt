package com.tistory.eclipse4j.core.config.ai

import dev.langchain4j.memory.ChatMemory
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.store.memory.chat.redis.RedisChatMemoryStore
import org.springframework.stereotype.Component

@Component
class CounselorMessageChatMemoryFactory {

    private fun counselorRedisChatMemoryStore(): RedisChatMemoryStore {
        return RedisChatMemoryStore.builder()
            .host("localhost")
            .port(6379)
            .build()
    }

    fun createCounselorChatMemory(sessionId: String, maxMessages: Int = 20): ChatMemory {
        return MessageWindowChatMemory.builder()
            .id(sessionId)
            .chatMemoryStore(counselorRedisChatMemoryStore())
            .maxMessages(maxMessages)
            .build()
    }
}
