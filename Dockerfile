
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the app
RUN chmod +x mvnw && ./mvnw clean package

# Run the app
CMD ["java", "-jar", "target/web-0.0.1-SNAPSHOT.jar"]