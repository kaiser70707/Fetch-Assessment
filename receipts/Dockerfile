FROM openjdk:21-slim
WORKDIR /app
COPY receipts-0.0.1-SNAPSHOT.jar /app/receipts.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/receipts.jar"]
