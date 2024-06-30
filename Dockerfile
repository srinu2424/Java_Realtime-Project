FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/JSB-PROJECT-1.0.0-SNAPSHOT.jar /app
EXPOSE 9090
CMD ["java", "-jar", "/app/JSB-PROJECT-1.0.0-SNAPSHOT.jar"]

