group = "com.example.storage.jpa"

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    api(project(":core-module"))
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
}
