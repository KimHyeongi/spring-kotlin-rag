package com.tistory.eclipse4j.core.domain.base.condition

data class ApiPageCondition(
    var page: Long = 1,
    var size: Long = 10
) {
    fun offset(): Long {
        return if (page > 0) (page - 1) * size else page * size
    }

    fun limit(): Long {
        return size + 1
    }
}
