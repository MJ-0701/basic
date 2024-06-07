# 멀티스테이지 빌드를 위한 베이스 이미지
FROM gradle:8.5-jdk21-alpine as builder

# 작업 디렉토리 설정
WORKDIR /app

# 최상위 모듈에 settings.gradle.kts와 build.gradle.kts 파일 복사
COPY settings.gradle.kts build.gradle.kts ./

# api 모듈의 build.gradle.kts 파일 복사
COPY internal-api-module/build.gradle.kts internal-api-module/

# 소스 코드 복사
COPY . .

# api 모듈의 애플리케이션 빌드
RUN ./gradlew --project-dir=internal-api-module clean build --exclude-task test

# 최종 이미지를 위한 베이스 이미지
#FROM openjdk:17.0.2-jdk
FROM eclipse-temurin:21-jre

# 작업 디렉토리 설정
WORKDIR /app
ENV USE_PROFILE local

# api 모듈의 빌드 결과물 복사
COPY --from=builder /app/internal-api-module/build/libs/*.jar app.jar

# 실행 명령
ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar", "app.jar"]

