# Use a minimal JDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file from your local system to the container
COPY target/IRCTC_REST_API-0.0.1-SNAPSHOT.jar irctc_test_api.jar

# Expose port 8082
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "irctc_test_api.jar"]
