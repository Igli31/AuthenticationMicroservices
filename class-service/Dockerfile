# Use official OpenJDK 17 image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the jar (after building with Maven)
COPY target/class-service-0.0.1-SNAPSHOT.jar class-service.jar

# Expose port (matches application.properties/server.port)
EXPOSE 8082

# Command to run the jar
ENTRYPOINT ["java","-jar","class-service.jar"]
