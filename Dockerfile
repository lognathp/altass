# Use an official OpenJDK as a base image
FROM openjdk:17-jdk-slim as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/altass.jar /app/altass.jar

# Expose the port your application will run on (e.g., 8080 for Spring Boot)
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "/app/altass.jar"]