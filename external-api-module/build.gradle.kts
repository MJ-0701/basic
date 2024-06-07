tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}


dependencies {
    api(project(":core-module"))
    implementation("commons-codec:commons-codec:1.15")
    implementation("net.sf.json-lib:json-lib:2.4:jdk15")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    // https://mvnrepository.com/artifact/com.google.api-client/google-api-client
    implementation("com.google.api-client:google-api-client:2.2.0")
    // https://mvnrepository.com/artifact/com.google.api-client/google-api-client-gson
    implementation("com.google.api-client:google-api-client-gson:2.4.0")
    // https://mvnrepository.com/artifact/com.google.auth/google-auth-library-oauth2-http
    implementation("com.google.auth:google-auth-library-oauth2-http:1.23.0")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.3")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    // https://mvnrepository.com/artifact/jakarta.xml.bind/jakarta.xml.bind-api
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    // thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

//    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//    implementation("org.springframework.cloud:spring-cloud-starter-config")
//    implementation ("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
//    implementation("org.springframework.cloud:spring-cloud-starter-config:4.1.1")

}