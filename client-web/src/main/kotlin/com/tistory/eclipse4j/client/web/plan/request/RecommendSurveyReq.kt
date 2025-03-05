package com.tistory.eclipse4j.client.web.plan.request

data class RecommendSurveyReq(
    val telecomCompany: String? = "",
    val usageApps: List<String> = emptyList(),
    val usagePatternInfo: String? = "",
) {
    fun toPatternInfo(): String {
        return """
            $usagePatternInfo
            -----
            """.trimIndent()
    }
}
