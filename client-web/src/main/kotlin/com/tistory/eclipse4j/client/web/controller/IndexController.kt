package com.tistory.eclipse4j.client.web.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping(value = ["", "/"])
    fun index(request: HttpServletRequest): String {
        return "index"
    }

    @GetMapping(value = ["/health"])
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }

    @GetMapping(value = ["/403"])
    fun error403(): String {
        return "403"
    }

    @GetMapping(value = ["/500"])
    fun error500(): String {
        return "500"
    }
}
