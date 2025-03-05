package com.tistory.eclipse4j.client.web.example.controller

import com.tistory.eclipse4j.client.web.example.request.ContentsReq
import com.tistory.eclipse4j.core.domain.ai.model.ProfanityWordResponse
import com.tistory.eclipse4j.core.domain.ai.service.CounselorAgentService
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
class CounselorController(
    private val counselorAgentService: CounselorAgentService,
) {

    @GetMapping("/counselor")
    fun counselor(model: Model): String {
        model.addAttribute("contents", ContentsReq.init())
        return "pages/examples/contents-counselor"
    }

    @PostMapping("/counselor")
    fun counselor(req: ContentsReq, model: Model): String {
        val counselor = counselorAgentService.find(req.contents!!)
        model.addAttribute("contents", req)
        model.addAttribute("counselor", counselor.trim())
        return "pages/examples/contents-counselor"
    }
}
