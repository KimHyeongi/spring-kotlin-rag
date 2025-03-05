package com.tistory.eclipse4j.core.domain.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

internal class SpringKotestExampleTest : StringSpec() {
    init {
        "샘플 테스트" {
            val successResult = "OK-SUCCESS"
            every { mockService.example("SUCCESS") } returns successResult
            val result = sut.example("SUCCESS")
            result shouldBe "OK-SUCCESS"
            verify (exactly = 1) { mockService.example(any()) }
        }
    }

    private val mockService = mockk<SpringService>()
    private val sut = SutSpringService(
        mockService,
    )
}

@Service
@Profile("test")
class SpringService {
    fun example(p: String) : String {
        return "OK-$p"
    }
}

@Service
@Profile("test")
class SutSpringService(
    private val mockService: SpringService,
) {
    fun example(p: String): String {
        return mockService.example(p)
    }
}

