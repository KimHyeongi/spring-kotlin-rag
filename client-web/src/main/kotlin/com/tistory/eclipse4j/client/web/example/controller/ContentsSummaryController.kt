package com.tistory.eclipse4j.client.web.example.controller

import com.tistory.eclipse4j.client.web.example.request.ContentsReq
import com.tistory.eclipse4j.core.domain.ai.model.ContentsSummaryResponse
import com.tistory.eclipse4j.core.domain.ai.service.ContentsSummaryAgentService
import com.tistory.eclipse4j.core.domain.ai.service.ContentsSummaryJsonToObjectService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/examples")
class ContentsSummaryController(
    private val contentsSummaryAgentService: ContentsSummaryAgentService,
    private val contentsSummaryJsonToObjectService: ContentsSummaryJsonToObjectService
) {
    @GetMapping("/summary")
    fun summary(model: Model): String {
        model.addAttribute("summary", ContentsSummaryResponse(
            summary = "", sentiment = "", additionalInsights = mapOf(), tags = listOf()
        ))
        model.addAttribute("contents", ContentsReq.init())
        return "pages/examples/contents-summary"
    }

    @PostMapping("/summary")
    fun summary(req: ContentsReq, model: Model): String {
        val summaryJson = contentsSummaryAgentService.summarize(req.contents!!)
        val summary = contentsSummaryJsonToObjectService.parseJson(summaryJson)
        model.addAttribute("summary", summary)
        model.addAttribute("contents", req)
        return "pages/examples/contents-summary"
    }
}
