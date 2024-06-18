# FROM openjdk:8-jdk-alpine
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder

WORKDIR /src
COPY . .

RUN mvn clean compile package

FROM openjdk:17-jdk-alpine AS runtime

WORKDIR /app
COPY --from=builder /src/target/*.jar ./app.jar

EXPOSE 8080

CMD java -jar ./app.jar

