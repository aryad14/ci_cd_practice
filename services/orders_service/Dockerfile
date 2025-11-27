FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml file first to resolve dependencies
COPY pom.xml ./

# Cache Maven dependencies
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q dependency:resolve

# Copy the source code
COPY src ./src

# Build the application
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
