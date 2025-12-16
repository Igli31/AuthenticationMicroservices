# Use official OpenJDK 17 image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the jar (after building with Maven)
COPY target/student-service-0.0.1-SNAPSHOT.jar student-service.jar

# Expose port (matches application.properties/server.port)
EXPOSE 8081

# Command to run the jar
ENTRYPOINT ["java","-jar","student-service.jar"]