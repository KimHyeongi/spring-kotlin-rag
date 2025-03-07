package com.tistory.eclipse4j.core.config.ai

import dev.langchain4j.store.memory.chat.redis.RedisChatMemoryStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CounselorRedisChatMemoryStore(
    @Value("\${spring.data.redis.host}")
    private val host: String?,
    @Value("\${spring.data.redis.port}")
    private val port: Int?,
) : RedisChatMemoryStore(host, port, null, null)
