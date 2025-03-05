package com.tistory.eclipse4j.core.config.cache

enum class RedisCacheKeyProperties(
    val description: String,
    val ttl: Long,
    val key: Boolean,
    val placeholder: String,
    val dpOrderNumber: Int
) {
    cached_default(
        "기본캐시 1분",
        60,
        true,
        "KEY",
        0
    )
}
