group = "com.example.core.module"

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}


plugins{
    kotlin("kapt") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
}

dependencies {
    // H2
    runtimeOnly("com.h2database:h2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    kapt("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.persistence:jakarta.persistence-api")
    kapt("jakarta.annotation:jakarta.annotation-api")


    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
    sourceSets.main {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/source/kapt").get().asFile.path)
    }
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}
