package com.tistory.eclipse4j.core.domain.ai.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ContentsSummaryResponse(
    /** 요약 */
    val summary: String? = null,
    /** 감정 분석 결과 (긍정/부정/중립) */
    val sentiment: String? = null,
    /** 추가 분석 정보 (선택사항) */
    val additionalInsights: Map<String, String>?,
    /** 태그 목록 (선택사항) */
    val tags: List<String> = listOf()
) {
    fun getTakeTags() = tags.map { "#" + it.replace(" ", "") }.take(3)
    fun getContentsSummary() = summary
}
