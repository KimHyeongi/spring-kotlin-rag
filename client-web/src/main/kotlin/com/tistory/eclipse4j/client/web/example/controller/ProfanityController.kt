package com.tistory.eclipse4j.client.web.example.controller

import com.tistory.eclipse4j.client.web.example.request.ContentsReq
import com.tistory.eclipse4j.core.domain.ai.model.ProfanityWordResponse
import com.tistory.eclipse4j.core.domain.ai.service.ProfanityAgentService
import com.tistory.eclipse4j.core.domain.ai.service.ProfanityJsonToListService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/examples")
class ProfanityController(
    private val profanityAgentService: ProfanityAgentService,
    private val profanityJsonToListService: ProfanityJsonToListService
) {

    @GetMapping("/profanity")
    fun profanity(model: Model): String {
        model.addAttribute("profanity", ContentsReq.init())
        model.addAttribute("profanities", listOf<ProfanityWordResponse>())
        return "pages/examples/contents-profanity"
    }

    @PostMapping("/profanity")
    fun profanity(req: ContentsReq, model: Model): String {
        val profanitiesJson = profanityAgentService.find(req.contents!!)
        val profanities = profanityJsonToListService.parseProfanityJson(profanitiesJson)
        model.addAttribute("profanity", req)
        model.addAttribute("profanities", profanities)
        return "pages/examples/contents-profanity"
    }
}
