plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3")
    }
}

dependencies {
    api(project(":storage-module:db-jpa"))
    api(project(":core-module"))
    api(project(":external-api-module"))

    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")


    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.3")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")

    // S3
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

    // batch
    implementation("org.springframework.boot:spring-boot-starter-batch")

    // excel lib
    implementation("org.apache.poi:poi-ooxml:5.2.5")

}


tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

springBoot.mainClass.value("com.example.internal.api.module.BasicApplicationKt")

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

