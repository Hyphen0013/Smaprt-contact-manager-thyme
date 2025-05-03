# Use Java 21 base image (official Eclipse Temurin JDK)
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file (ensure the JAR name matches your Maven build output)
COPY target/your-app.jar /app/scm2.0-0.0.1-SNAPSHOT

# Expose the port your Spring Boot app uses
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "scm2.0-0.0.1-SNAPSHOT"]