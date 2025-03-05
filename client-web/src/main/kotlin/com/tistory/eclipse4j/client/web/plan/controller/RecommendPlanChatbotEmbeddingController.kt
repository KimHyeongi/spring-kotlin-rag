package com.tistory.eclipse4j.client.web.plan.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import com.tistory.eclipse4j.core.domain.ai.embedding.RecommendPlanChatbotVectorInitialService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/recommend-plans")
class RecommendPlanChatbotEmbeddingController(
    private val recommendPlanChatbotVectorInitialService: RecommendPlanChatbotVectorInitialService,
) {
    private val log = KotlinLogging.logger { }

    @GetMapping("/embedding")
    fun embedding(): String {
        return "pages/plans/embedding"
    }

    @PostMapping("/embedding")
    fun completedEmbedding(): String {
        //recommendPlanChatbotVectorInitialService.initializePlanEmbedding()
        recommendPlanChatbotVectorInitialService.initializePlanEmbedding4FileData()
        return "pages/plans/embedding"
    }
}
