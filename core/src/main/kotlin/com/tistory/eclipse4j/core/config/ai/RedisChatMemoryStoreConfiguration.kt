package com.tistory.eclipse4j.core.config.ai

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.store.memory.chat.ChatMemoryStore
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@Configuration
class RedisChatMemoryStoreConfiguration(
    private val redisTemplate: RedisTemplate<String, String>
) : ChatMemoryStore {

    private val maxHistorySize: Int = 10
    private val objectMapper = jacksonObjectMapper()

    private fun getRedisKey(memoryId: Any): String = "chat_memory:$memoryId"

    override fun getMessages(memoryId: Any): List<ChatMessage> {
        val key = getRedisKey(memoryId)
        val json = redisTemplate.opsForValue().get(key) ?: return emptyList()
        return try {
            objectMapper.readValue(json, object: TypeReference<List<ChatMessage>>() {})
        } catch (e: Exception) {
            throw RuntimeException("Failed to deserialize chat messages", e)
        }
    }

    override fun updateMessages(memoryId: Any, messages: List<ChatMessage>) {
        val key = getRedisKey(memoryId)
        try {
            val json = objectMapper.writeValueAsString(messages)
            redisTemplate.opsForValue().set(key, json)
            redisTemplate.opsForValue().getAndExpire(key, 3600, TimeUnit.SECONDS) // 1시간 후 자동 삭제 (선택 사항)
        } catch (e: Exception) {
            throw RuntimeException("Failed to serialize chat messages", e)
        }
    }

    override fun deleteMessages(memoryId: Any) {
        val key = getRedisKey(memoryId)
        redisTemplate.delete(key)
    }
}

/** Example
fun main() {
    // RedisChatMemoryStore 인스턴스 생성
    val memoryStore = RedisChatMemoryStore("redis://localhost:6379", 10)

    // ChatMemory 설정
    val chatMemory = TokenWindowChatMemory.builder()
        .chatMemoryStore(memoryStore)
        .maxTokens(1000, OpenAiTokenizer())
        .build()

    // OpenAI 모델 설정
    val model = OpenAiChatModel.builder()
        .apiKey("your-api-key")
        .build()

    // 대화 메시지 추가
    chatMemory.addMessage(ChatMessage.userMessage("안녕하세요!"))

    // 저장된 대화 불러오기
    val messages = chatMemory.messages()
    messages.forEach { message -> println(message.content) }

    // 메모리 해제
    memoryStore.close()
}
*/
