package com.tistory.eclipse4j.admin.web.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    private val log = KotlinLogging.logger { }
    @GetMapping(value = ["", "/"])
    fun index(): String {
        return "index"
    }
}
