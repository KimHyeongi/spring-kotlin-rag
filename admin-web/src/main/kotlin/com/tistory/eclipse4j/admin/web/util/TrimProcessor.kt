package com.tistory.eclipse4j.admin.web.util

import com.tistory.eclipse4j.admin.web.util.annotations.Trimmed

class TrimProcessor {
    companion object {
        fun process(obj: Any?) {
            if (obj == null) {
                return
            }
            obj::class.java.declaredFields.forEach {
                val trim = it.getAnnotation(Trimmed::class.java)
                if (trim != null) {
                    it.isAccessible = true
                    val value = it.get(obj)
                    if (value is String) {
                        it.set(obj, value.trim())
                    }
                }
            }
        }
    }
}
