# Dockerfile for OrderService
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar
ENTRYPOINT ["java", "-jar", "/order-service.jar"]
