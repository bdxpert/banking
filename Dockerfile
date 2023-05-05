#FROM openjdk:10-jre-slim
FROM eclipse-temurin:17-jdk-alpine

#FROM openjdk:17-alpine

WORKDIR /app
COPY ./target/SanBankApp-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "SanBankApp-0.0.1-SNAPSHOT.jar"]