package com.tistory.eclipse4j.core.domain.ai.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tistory.eclipse4j.core.domain.ai.model.ProfanityWordResponse
import org.springframework.stereotype.Service


@Service
class ProfanityJsonToListService {
    fun parseProfanityJson(json: String): List<ProfanityWordResponse> {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(json, object : TypeReference<List<ProfanityWordResponse>>() {})
    }
}

