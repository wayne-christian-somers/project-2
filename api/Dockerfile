FROM openjdk:8-jdk-alpine

# Copy the JAR from the target folder into the container
COPY /target/api-0.0.1-SNAPSHOT.jar api-0.0.1-SNAPSHOT.jar 
COPY /src/main/resources/Config.properties /src/main/resources/Config.properties
ENTRYPOINT ["java", "-jar", "/api-0.0.1-SNAPSHOT.jar"]