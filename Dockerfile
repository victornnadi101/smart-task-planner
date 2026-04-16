
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the app
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Expose Render port
EXPOSE 8080

# Run the app using dynamic port
CMD ["sh", "-c", "java -jar target/web-0.0.1-SNAPSHOT.jar --server.port=${PORT}"]