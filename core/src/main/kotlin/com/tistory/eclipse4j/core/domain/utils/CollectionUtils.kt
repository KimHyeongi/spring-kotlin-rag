package com.tistory.eclipse4j.core.domain.utils

class CollectionUtils {
    companion object {
        fun <T> isEqual(first: List<T>, second: List<T>): Boolean {
            if (first.size != second.size) {
                return false
            }
            return first.zip(second).all { (x, y) -> x == y }
        }
    }
}
