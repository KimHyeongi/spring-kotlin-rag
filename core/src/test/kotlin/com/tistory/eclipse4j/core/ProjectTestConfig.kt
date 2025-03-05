package com.tistory.eclipse4j.core

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import io.kotest.core.test.AssertionMode
import io.mockk.clearAllMocks

object ProjectTestConfig : AbstractProjectConfig() {
    override val parallelism = 3
    override val assertionMode = AssertionMode.Error
    override val globalAssertSoftly = true
    override val failOnIgnoredTests = false
    override val isolationMode = IsolationMode.InstancePerTest

    override suspend fun afterProject() {
        clearAllMocks()
    }
}
