package com.tistory.eclipse4j.core.domain.utils

import java.time.LocalDate
import java.time.LocalDateTime

fun LocalDate.atMinTime(): LocalDateTime =
    this.atTime(0, 0, 0)

fun LocalDate.atMaxTime(): LocalDateTime =
    this.atTime(23, 59, 59)

fun LocalDateTime.toUtc(): LocalDateTime =
    DateTimeUtils.convertToUtcTime(this)

fun LocalDateTime.toKst(): LocalDateTime =
    DateTimeUtils.convertToKoreanTime(this)
