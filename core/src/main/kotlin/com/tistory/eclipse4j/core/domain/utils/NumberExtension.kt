package com.tistory.eclipse4j.core.domain.utils

import java.math.BigDecimal
import java.text.NumberFormat

fun Int.toTextFormat(): String {
    return NumberFormat.getInstance().format(this)
}

fun Long.toTextFormat(): String {
    return NumberFormat.getInstance().format(this)
}

fun BigDecimal.toTextFormat(): String {
    return NumberFormat.getInstance().format(this)
}
