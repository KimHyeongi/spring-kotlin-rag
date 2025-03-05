package com.tistory.eclipse4j.admin.web.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder

@ControllerAdvice
class ExceptionController {

    val log = KotlinLogging.logger { }

    @ExceptionHandler(AccessDeniedException::class)
    fun accessDenied(ex: Exception): String {
        log.error(ex) { "AccessDeniedException : ${ex.message}" }
        return "pages/login/access_denied"
    }

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception): String {
        log.error(ex) { "Exception : ${ex.message}" }
        return "/500"
    }

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.registerCustomEditor(String::class.java, StringTrimmerEditor(true))
    }
}
