package com.tistory.eclipse4j.core.context

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Configuration
import kotlin.reflect.KClass

@Configuration(proxyBeanMethods = false)
class ApplicationContextProvider : ApplicationContextAware {
    companion object {
        private lateinit var applicationContext: ApplicationContext
        fun getContext(): ApplicationContext {
            return applicationContext
        }

        // 타입으로 빈을 가져옵니다
        fun <T : Any> getBean(type: KClass<T>): T =
            applicationContext.getBean(type.java)

        // 이름과 타입으로 빈을 가져옵니다
        fun <T : Any> getBean(name: String, type: KClass<T>): T =
            applicationContext.getBean(name, type.java)
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        ApplicationContextProvider.applicationContext = applicationContext
    }
}
