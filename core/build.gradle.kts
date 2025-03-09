plugins {
    id("java-library")
    id("java-test-fixtures")
    id("com.google.devtools.ksp") version Versions.kotlin_ksp
    kotlin("plugin.jpa") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-cache")
    api("org.springframework.boot:spring-boot-starter-data-redis")
    api("org.springframework.retry:spring-retry")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:${Versions.spring_cloud_aws_dep_version}"))

    implementation("software.amazon.awssdk:core:${Versions.awssdk_version}")
    implementation("software.amazon.awssdk:s3:${Versions.awssdk_version}")
    implementation("software.amazon.awssdk:sts:${Versions.awssdk_version}")
    implementation("software.amazon.awssdk:sqs:${Versions.awssdk_version}")
    implementation("software.amazon.awssdk.crt:aws-crt:${Versions.aws_crt_version}")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_version}")
    implementation("org.javers:javers-spring-boot-starter-sql:${Versions.javers}")

    implementation("io.github.oshai:kotlin-logging:${Versions.kotlin_logging}")
    implementation("io.awspring.cloud:spring-cloud-aws-starter:${Versions.io_awspring_version}")
    implementation("io.awspring.cloud:spring-cloud-aws-starter-s3:${Versions.io_awspring_version}")
    implementation("io.awspring.cloud:spring-cloud-aws-starter-sqs:${Versions.io_awspring_version}")
    implementation("io.hypersistence:hypersistence-utils-hibernate-62:${Versions.hypersistence_utils_62}")

    implementation("dev.langchain4j:langchain4j-open-ai-spring-boot-starter:${Versions.langchain4j}")
    implementation("dev.langchain4j:langchain4j-ollama-spring-boot-starter:${Versions.langchain4j}")
    implementation("dev.langchain4j:langchain4j-spring-boot-starter:${Versions.langchain4j}")
    implementation("dev.langchain4j:langchain4j-pgvector:${Versions.langchain4j_b}")
    implementation("dev.langchain4j:langchain4j-redis:${Versions.langchain4j_b}")



    implementation("org.postgresql:postgresql")
    implementation("com.h2database:h2")

    implementation("io.github.openfeign.querydsl:querydsl-jpa:${Versions.openfeign_querydsl_version}")
    ksp("io.github.openfeign.querydsl:querydsl-ksp-codegen:${Versions.openfeign_querydsl_version}")

    implementation("org.apache.httpcomponents.client5:httpclient5:${Versions.http_client_version}")
    implementation("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations:${Versions.jackson}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    implementation("com.slack.api:slack-api-client:${Versions.slack}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(testFixtures(project(":core")))
}

sourceSets["main"].java.srcDirs("build/generated/ksp/main/kotlin")
tasks.register("prepareKotlinBuildScriptModel") {}
