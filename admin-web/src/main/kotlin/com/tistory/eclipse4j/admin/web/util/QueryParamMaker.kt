package com.tistory.eclipse4j.admin.web.util

import com.tistory.eclipse4j.admin.web.util.annotations.QueryParam

class QueryParamMaker {
    companion object {
        fun make(f: Any?): String {
            if (f == null) {
                return ""
            }
            val queryString = StringBuilder()
            f::class.java.declaredFields.forEach {
                val query = it.getAnnotation(QueryParam::class.java)
                if (query != null) {
                    it.isAccessible = true
                    val value = it.get(f)
                    queryString.append(it.name)
                        .append("=")
                        .append(value ?: "")
                        .append("&")
                }
            }
            return queryString.toString()
        }
    }
}
