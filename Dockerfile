# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# mvnw can lose executable bit when committed from Windows; ensure it's runnable in Linux
RUN chmod +x mvnw && ./mvnw -DskipTests clean package

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
CMD ["sh","-c","java $JAVA_OPTS -jar app.jar --server.port=${PORT:-8080}"]
