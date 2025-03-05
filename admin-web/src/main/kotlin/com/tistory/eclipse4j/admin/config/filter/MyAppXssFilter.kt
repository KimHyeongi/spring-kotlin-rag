package com.tistory.eclipse4j.admin.config.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist
import org.springframework.stereotype.Component

@Suppress("USELESS_CAST")
@Component
class MyAppXssFilter : Filter {

    private val excludePrefixUrls = listOf("/promotions", "/emails")

    override fun init(filterConfig: FilterConfig?) {}

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val match = excludePrefixUrls.any { req.requestURI.startsWith(it) }
        if (match && req.method == "POST") {
            chain.doFilter(request, response)
            return
        }
        chain.doFilter(XSSRequestWrapper(request as HttpServletRequest), response)
    }

    override fun destroy() {}

    private class XSSRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
        override fun getParameter(name: String): String? {
            return super.getParameter(name)?.let { clean(it) }
        }

        override fun getParameterValues(name: String): Array<String>? {
            return super.getParameterValues(name)?.map { clean(it) }?.toTypedArray()
        }

        override fun getHeader(name: String): String? {
            return super.getHeader(name)?.let { clean(it) }
        }

        companion object {
            fun clean(value: String): String {
                return Jsoup.clean(value, Safelist.relaxed())
            }
        }
    }
}
