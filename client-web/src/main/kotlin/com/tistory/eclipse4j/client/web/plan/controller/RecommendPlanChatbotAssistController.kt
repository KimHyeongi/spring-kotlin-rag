package com.tistory.eclipse4j.client.web.plan.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import com.tistory.eclipse4j.client.web.plan.request.RecommendSurveyReq
import com.tistory.eclipse4j.core.domain.ai.embedding.RecommendPlanChatbotVectorInitialService
import com.tistory.eclipse4j.core.domain.ai.service.RecommendPlanChatbotAgentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/recommend-plans")
class RecommendPlanChatbotAssistController(
    private val recommendPlanChatbotAgentService: RecommendPlanChatbotAgentService
) {
    private val log = KotlinLogging.logger { }

    @GetMapping("/user-survey")
    fun survey(): String {
        return "pages/plans/recommend_plan_survey"
    }

    @PostMapping("/user-survey-send")
    fun survey(form: RecommendSurveyReq, model: Model): String {
        val monthlyUsage = recommendPlanChatbotAgentService.chatAi(form.toPatternInfo())
        val reasons = recommendPlanChatbotAgentService.recommendPlanForSizeChatAi(monthlyUsage.perMonth!!)
        reasons.map {
            it.appendMonthlyUsage(monthlyUsage)
        }
        model.addAttribute("reasons", reasons)
        return "pages/plans/recommend_plan_results"
    }
}
