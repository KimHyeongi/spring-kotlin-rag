package com.tistory.eclipse4j.core.domain.base.entity

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class JsonConverter : AttributeConverter<Any, String> {

    companion object {
        private val objectMapper = ObjectMapper()
    }

    override fun convertToDatabaseColumn(attribute: Any?): String? {
        if (attribute == null) return null
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Any? {
        if (dbData == null) return null

        try {
            val jsonNode: JsonNode = objectMapper.readTree(dbData)
            // 배열 형태의 JSON 데이터 처리
            if (jsonNode.isArray) {
                return objectMapper.readValue(dbData, List::class.java)
            }
            // 객체 형태의 JSON 데이터 처리
            else if (jsonNode.isObject) {
                return objectMapper.readValue(dbData, Map::class.java)
            }
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException("Invalid JSON data", e)
        }

        return null
    }
}
