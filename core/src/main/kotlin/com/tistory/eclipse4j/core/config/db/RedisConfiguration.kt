package com.tistory.eclipse4j.core.config.db

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

/**
 * Repository 만 활성화 하도록 한다.
 */
@Configuration
@EnableRedisRepositories(basePackages = ["com.tistory.eclipse4j.core.redis"])
class RedisConfiguration() {
    /** 생성자 */
    // private val redisProperties: RedisProperties

    /** 본문 */
    //    @Bean
    //    fun redisConnectionFactory(): RedisConnectionFactory {
    //        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    //    }
    //
    //    @Bean
    //    fun redisTemplate(): RedisTemplate<*, *> {
    //        return RedisTemplate<Any, Any>().apply {
    //            this.connectionFactory = redisConnectionFactory()
    //            this.keySerializer = StringRedisSerializer()
    //            this.hashKeySerializer = StringRedisSerializer()
    //            this.valueSerializer = StringRedisSerializer()
    //        }
    //    }
}
