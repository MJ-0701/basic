import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0" apply false
    id("io.spring.dependency-management") version "1.1.5" apply false
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24" apply false
    kotlin("plugin.jpa") version "1.9.24" apply false
    kotlin("kapt") version "1.9.24" apply false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allprojects {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")


    }

    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        gradlePluginPortal()
    }

    dependencies {
        implementation("io.spring.gradle:dependency-management-plugin:1.1.4")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.json:json:20240205")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2") // coroutine
        testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2") // 테스트
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }


}

val apiProject = listOf(
    project("internal-api-module"),
    project("external-api-module")
)

configure(apiProject) {
    // 아직 스프링부트 3.3.0 버전에 호환되는 클라우드 버전이 나오지 않았기 때문에 추후 release 시 버전 변경
    val springCloudVersion = "2023.0.0"
    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")

        }
    }
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-batch")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.springframework.batch:spring-batch-test")
        testImplementation("org.springframework.security:spring-security-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.jetbrains.kotlin:kotlin-test")

        // https://mvnrepository.com/artifact/com.google.code.gson/gson
        implementation("com.google.code.gson:gson:2.10.1")


    }
}


val queryDslProject = listOf(
    project("core-module"),
    project("storage-module"),
    project("storage-module:db-jpa")
)

configure(queryDslProject) {
    apply {
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("kotlin-kapt")

    }

    allOpen {
        annotation("jakarta.persistence.Inheritance")
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.mysql:mysql-connector-j")

        // Querydsl 추가
        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
        implementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
        implementation("jakarta.persistence:jakarta.persistence-api")
        implementation("jakarta.annotation:jakarta.annotation-api")

        // https://mvnrepository.com/artifact/com.google.code.gson/gson
        implementation("com.google.code.gson:gson:2.10.1")
        // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
        implementation("com.squareup.okhttp3:okhttp:4.10.0-RC1")
    }

}
