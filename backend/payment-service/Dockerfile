FROM openjdk:11-jre-slim
COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar
ENTRYPOINT ["java", "-jar", "/payment-service.jar"]