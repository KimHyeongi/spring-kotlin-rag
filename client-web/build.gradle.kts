plugins {
}

dependencies {
    implementation(project(":core"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.session:spring-session-data-redis")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    // firebase
    implementation("com.google.firebase:firebase-admin:9.4.3")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
    implementation("org.dhatim:fastexcel:0.18.4")
    implementation("org.dhatim:fastexcel-reader:0.18.4")
    implementation("org.javers:javers-spring-boot-starter-sql:7.7.0")
    implementation("org.jsoup:jsoup:1.18.3")
    implementation("org.apache.commons:commons-text:1.13.0")
    implementation("org.apache.commons:commons-imaging:1.0.0-alpha5")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(testFixtures(project(":core")))
}

tasks.register("prepareKotlinBuildScriptModel") {}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    mainClass.set("com.tistory.eclipse4j.client.ClientApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}
