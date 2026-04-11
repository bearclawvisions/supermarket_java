# Build stage
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /app

# Copy the entire project
COPY . .

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app

COPY --from=builder /app/api/target/*.jar app.jar

# [Debian] Make sure to run the container not as root. rootless container would be better
RUN groupadd --system javauser && \
    useradd --system --gid javauser --shell /bin/false --home /app javauser
RUN chown -R javauser:javauser /app
USER javauser

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]