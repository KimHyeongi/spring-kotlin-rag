package com.tistory.eclipse4j.core.domain.ai.model

import java.io.Serializable

data class RecommendPlanResponse(
    val type: String? = null,
    /** 추천요금제 */
    val recommendPlan: String? = null,

    val recommendPlanInfo: String? = null,

    /** 추천요금제 추천 설명 */
    val recommendReasons: String? = null,

    var monthlyUsage: UsageDataSizePerMonthResponse? = null,

) : Serializable {

    fun appendMonthlyUsage(monthlyUsage: UsageDataSizePerMonthResponse) {
        this.monthlyUsage = monthlyUsage
    }

    companion object {
        fun emptyResultMessage(): RecommendPlanResponse {
            return RecommendPlanResponse(
                recommendPlan = null,
                recommendPlanInfo = null,
                recommendReasons = "추천 요금제를 찾을 수 없습니다. 상담 진행을 할 수 없습니다. 상담사를 호출합니다.",
                monthlyUsage = null
            )
        }

        fun unLimitPlan(): RecommendPlanResponse {
            return RecommendPlanResponse(
                recommendPlan = "59요금제",
                recommendPlanInfo = "데이터 무제한으로 사용할 수 있습니다.",
                recommendReasons = "'59요금제'는 무제한 데이터를 제공합니다.- 📺 모든 화질의 유튜브: 무제한- 🎵 음악 스트리밍: 무제한- 💰 결합 할인: 최대 14,000원 할인 가능- 🛍 네이버페이 지원: 가입 1개월 후부터 매달 20,000원 지급됩니다.",
                monthlyUsage = UsageDataSizePerMonthResponse()
            )
        }
    }
}
