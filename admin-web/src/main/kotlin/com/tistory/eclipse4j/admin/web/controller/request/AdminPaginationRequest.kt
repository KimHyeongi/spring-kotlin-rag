package com.tistory.eclipse4j.admin.web.controller.request

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class AdminPaginationRequest(
    var page: Int = 1,
    var size: Int = 10,
) {
    fun pageable(): Pageable {
        val offset = if (page > 0) page - 1 else 0
        return PageRequest.of(offset, size)
    }
}
