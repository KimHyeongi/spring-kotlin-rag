package com.tistory.eclipse4j.core.domain.ai.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class UsageDataSizePerMonthResponse(
    val description: String? = null,

    val perMonth: Long? = 0,

    val calculationDetails: String? = null,


) : Serializable {
    companion object {
        fun emptyResultMessage(): UsageDataSizePerMonthResponse {
            return UsageDataSizePerMonthResponse(
                description = null, perMonth = 0, calculationDetails = null
            )
        }
    }
}
