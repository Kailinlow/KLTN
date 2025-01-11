FROM openjdk:23

WORKDIR /app

COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar /app

EXPOSE 8888

CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]