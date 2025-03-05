package com.tistory.eclipse4j.core.config.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import java.time.Duration

/**
 * Application 내 단일 Redis 사용시
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
class RedisCacheSingleConfiguration {

    @Bean
    fun redisCacheManagerCustomizer() = RedisCacheManagerBuilderCustomizer { builder ->
        builder.withInitialCacheConfigurations(getCacheKeyValuesMap())
    }

    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerKotlinModule()
            .activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder()
                    .allowIfBaseType(Any::class.java).build(),
                ObjectMapper.DefaultTyping.EVERYTHING
            )
            .registerModule(JavaTimeModule())
    }
    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration? {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(60))
            .disableCachingNullValues()
            .serializeValuesWith(
                SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer(objectMapper())
                )
            )
    }

    private fun getCacheKeyValuesMap() = RedisCacheKeyProperties.entries
        .associate { it.name to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(it.ttl)) }
}
