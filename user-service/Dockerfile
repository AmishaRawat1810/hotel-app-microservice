# Build Stage
FROM gradle:jdk25 AS builder
WORKDIR /app

COPY . .

RUN gradle clean build -x test

# Run Stage
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]