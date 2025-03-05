import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin
    kotlin("plugin.jpa") version Versions.kotlin apply false
    kotlin("plugin.noarg") version Versions.kotlin
    kotlin("plugin.allopen") version Versions.kotlin
    id("org.springframework.boot") version Versions.spring_boot
    id("io.spring.dependency-management") version Versions.dependency_management
    idea
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of("21"))
    }
}
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

allprojects {
    group = "com.tistory.eclipse4j"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://repo.spring.io/release")
        }
        maven {
            url = uri("https://repository.jboss.org/maven2")
        }
        maven {
            url = uri("https://repo.spring.io/milestone")
        }
        maven {
            url = uri("https://repo.spring.io/snapshot")
        }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-noarg")
    apply(plugin = "kotlin-allopen")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_stdlib}")
        implementation("io.github.oshai:kotlin-logging:${Versions.kotlin_logging}") // microutils -> oshai
        implementation("ch.qos.logback.access:common:${Versions.logback_common}")
        implementation("ch.qos.logback:logback-core:${Versions.logback_classic}")
        implementation("ch.qos.logback:logback-classic:${Versions.logback_classic}")
        implementation("net.logstash.logback:logstash-logback-encoder:${Versions.logstash_logback_encoder}")
        testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit_jupiter}")

        testImplementation(platform("io.kotest:kotest-bom:${Versions.kotest_version}"))
        testImplementation("io.kotest:kotest-runner-junit5-jvm:${Versions.kotest_version}")
        testImplementation("io.kotest:kotest-assertions-core:${Versions.kotest_version}")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:${Versions.kotest_extensions_spring}")
        testImplementation("io.kotest:kotest-runner-junit5")
        testImplementation("io.mockk:mockk:${Versions.mock}")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    configurations.forEach {
        it.exclude(group = "commons-logging", module = "commons-logging")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.spring_boot}")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.spring_boot_cloud}")
            mavenBom("org.springframework.ai:spring-ai-bom:1.0.0-SNAPSHOT")
        }

        dependencies {
            dependency("net.logstash.logback:logstash-logback-encoder:${Versions.logstash_logback_encoder}")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
        systemProperty("kotest.framework.classpath.scanning.autoscan.disable", "true") // 필요시 "false"로 변경
        jvmArgs(
            "--enable-preview", // Java 21 미리보기 기능 활성화 (필요시)
            "-XX:+EnableDynamicAgentLoading",
            "-Djdk.instrument.traceUsage",
            "-XX:+UnlockExperimentalVMOptions", // 실험적 옵션 사용 가능
            "-XX:+UseZGC", // ZGC를 사용하여 GC 성능 최적화
            "-Xmx2G", // JVM 힙 메모리 조정
            "-Xshare:off",
            "-XX:+TieredCompilation", // JIT 최적화 관련 옵션
            "-XX:TieredStopAtLevel=1" // 빠른 실행을 위한 설정
        )
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    // Integration - Test
    sourceSets {
        create("integrationTest") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
        }
    }

    val integrationTestImplementation by configurations.getting {
        extendsFrom(configurations.implementation.get())
    }

    configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())
    configurations["integrationTestImplementation"].extendsFrom(configurations.testImplementation.get())
    configurations["integrationTestImplementation"].extendsFrom(configurations.implementation.get())

    val integrationTest = task<Test>("integrationTest") {
        description = "Runs integration tests."
        group = "verification"
        testClassesDirs = sourceSets["integrationTest"].output.classesDirs
        classpath = sourceSets["integrationTest"].runtimeClasspath
        useJUnitPlatform()
        shouldRunAfter("test")
    }

    tasks.check { dependsOn(integrationTest) }
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

project(":core") {
    val jar: Jar by tasks
    val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true
}
