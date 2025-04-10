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
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.session:spring-session-data-redis")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    // firebase
    implementation("com.google.firebase:firebase-admin:${Versions.firebase_admin}")

    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")
    implementation("org.dhatim:fastexcel:${Versions.fastexcel}")
    implementation("org.dhatim:fastexcel-reader:${Versions.fastexcel}")
    implementation("org.javers:javers-spring-boot-starter-sql:${Versions.javers}")
    implementation("org.jsoup:jsoup:${Versions.jsoup}")
    implementation("org.apache.commons:commons-text:${Versions.commons_text}")
    implementation("org.apache.commons:commons-imaging:${Versions.commons_imaging}")

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
    mainClass.set("com.tistory.eclipse4j.admin.AdminApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}

tasks.test {
    exclude("**/*integrationTest*")
}
