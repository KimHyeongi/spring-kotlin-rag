package com.tistory.eclipse4j.core.domain.ai.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tistory.eclipse4j.core.domain.ai.model.ContentsSummaryResponse
import org.springframework.stereotype.Service

@Service
class ContentsSummaryJsonToObjectService {
    fun parseJson(json: String): ContentsSummaryResponse {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(json, object : TypeReference<ContentsSummaryResponse>() {})
    }
}
