package com.tistory.eclipse4j.admin.web.util

import org.springframework.data.domain.Page
import org.springframework.ui.Model
import kotlin.math.max
import kotlin.math.min

class PaginationUtils {
    companion object {
        private fun <T> startPage(page: Page<T>): Int {
            if (page.totalPages <= 1) {
                return 1
            }
            return max(1, page.pageable.pageNumber - 10)
        }

        private fun <T> endPage(page: Page<T>): Int {
            if (page.totalPages <= 1) {
                return 1
            }
            val endPage = min(page.pageable.pageNumber + 10, page.totalPages)
            if (endPage == 0) {
                return 1
            }
            return endPage
        }

        /** 검색 폼의 attribute 명을 별도로 지정할 경우 */
        fun <T> addAttributePageInfo(model: Model, page: Page<T>, attributeKey: String = "searchForm") {
            model.addAttribute("queryString", QueryParamMaker.make(model.getAttribute(attributeKey)))
            model.addAttribute("pageStart", startPage(page))
            model.addAttribute("pageEnd", endPage(page))
        }
    }
}
