package com.tistory.eclipse4j.core.domain.utils

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

fun String.containsSpecialChar(): Boolean = Regex("[^A-Za-z0-9가-힣 ]").containsMatchIn(this)

fun String.toLocalDateOrNull(pattern: String? = null): LocalDate? = try {
    val formatter: DateTimeFormatterBuilder = DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ofPattern("yyyyMMdd"))
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))

    if (pattern != null) {
        formatter.appendPattern(pattern)
    }
    LocalDate.parse(this, formatter.toFormatter())
} catch (e: Exception) {
    null
}

fun String.toOffsetDateTimeOrNull(): OffsetDateTime? = try {
    val result = OffsetDateTime.parse(this, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    result
} catch (e: Exception) {
    null
}

fun String.ellipsis(length: Int): String {
    if (this.length <= length) return this
    return this.take(length) + "..."
}
