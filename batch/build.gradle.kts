plugins {
    id("com.google.osdetector") version "1.7.3"
}

dependencies {
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-batch")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_version}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.openapi}")
    implementation("org.springdoc:springdoc-openapi-starter-common:${Versions.openapi}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    implementation("commons-io:commons-io:2.18.0")
    implementation("com.google.firebase:firebase-admin:9.4.3")
    implementation("software.amazon.awssdk:sqs:2.30.14")
    implementation("net.javacrumbs.shedlock:shedlock-spring:4.25.0")
    implementation("net.javacrumbs.shedlock:shedlock-provider-redis-spring:4.25.0")
    if (osdetector.arch.equals("aarch_64")) {
        implementation("io.netty:netty-resolver-dns-native-macos:4.1.111.Final:osx-aarch_64")
    }
    implementation("org.jsoup:jsoup:1.17.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(testFixtures(project(":core")))
    testImplementation("org.springframework.batch:spring-batch-test")
}

tasks.register("prepareKotlinBuildScriptModel") {}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    mainClass.set("com.tistory.eclipse4j.batch.BatchApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}

tasks.test {
    exclude("**/*integrationTest*")
}
