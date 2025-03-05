package com.tistory.eclipse4j.core.domain.extendsfun

infix fun Boolean.then(action: () -> Unit): Boolean {
    if (this)
        action.invoke()
    return this
}

infix fun Boolean.elze(action: () -> Unit) {
    if (!this)
        action.invoke()
}
