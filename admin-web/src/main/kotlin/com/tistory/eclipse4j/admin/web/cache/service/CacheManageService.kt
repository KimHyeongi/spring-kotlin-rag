package com.tistory.eclipse4j.admin.web.cache.service

import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.stereotype.Service

@Service
class CacheManageService(
    private val cacheManager: CacheManager
) {
    fun evictSingleCacheValue(cacheName: String, cacheKey: String, cacheKeyValue: String) {
        cacheManager.getCache(cacheName)?.evict(cacheKeyValue)
    }

    fun evictSingleCacheValue(cacheName: String, cacheKey: String) {
        cacheManager.getCache(cacheName)?.evict(cacheKey)
    }

    fun evictSingleCacheValue(cacheName: String) {
        cacheManager.getCache(cacheName)?.evict(SimpleKey.EMPTY)
    }

    fun evictAllCaches() {
        cacheManager.cacheNames.stream()
            .forEach { cacheName: String? -> cacheManager.getCache(cacheName!!)!!.clear() }
    }

    fun evictCacheByName(cacheName: String) {
        cacheManager.getCache(cacheName)?.clear()
    }
}
