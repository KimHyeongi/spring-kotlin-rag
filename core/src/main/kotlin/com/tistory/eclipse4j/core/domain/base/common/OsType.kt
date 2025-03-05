package com.tistory.eclipse4j.core.domain.base.common

enum class OsType(
    val description: String
) {
    NONE("NONE"),
    ANDROID("안드로이드"),
    IOS("아이폰");

    companion object {
        fun findByName(name: String): OsType? {
            return entries.firstOrNull { it.name == name }
        }
    }
}
