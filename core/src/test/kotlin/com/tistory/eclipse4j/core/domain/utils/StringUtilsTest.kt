package com.tistory.eclipse4j.core.domain.utils

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringUtilsTest : StringSpec() {
    private val log = KotlinLogging.logger { }
    init {
        "StringUtils - UUID 를 12자리(Default) 문자로 반환한다." {
            val uuid = "b98e22a6-b540-4474-9d4e-5fb594109dca"
            val result = StringUtils.generate12CharUUID(uuid)
            result shouldBe "uY4iprVARHSd"
        }
    }
}
