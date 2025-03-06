package com.tistory.eclipse4j.client.web.example.controller

import com.tistory.eclipse4j.client.web.example.request.ContentsReq
import com.tistory.eclipse4j.core.config.ai.CounselorAgentServiceFactory
import com.tistory.eclipse4j.core.domain.ai.model.ProfanityWordResponse
import com.tistory.eclipse4j.core.domain.ai.service.CounselorAgentService
import com.tistory.eclipse4j.core.domain.ai.service.ProfanityAgentService
import com.tistory.eclipse4j.core.domain.ai.service.ProfanityJsonToListService
import feign.Response
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/examples")
class CounselorController(
    private val counselorAgentServiceFactory: CounselorAgentServiceFactory,
) {

    @GetMapping("/counselor")
    fun counselor(model: Model): String {
        model.addAttribute("contents", ContentsReq.init())
        return "pages/examples/contents-counselor"
    }

    @PostMapping("/counselor/{channelId}")
    fun counselor(@PathVariable("channelId") channelId: String, @RequestBody req: ContentsReq): ResponseEntity<String> {
        val counselorAgentService = counselorAgentServiceFactory.createCounselorAgent(channelId)
        val counselor = counselorAgentService.counseling(req.contents!!)
        return ResponseEntity.ok(counselor.trim())
    }
}
