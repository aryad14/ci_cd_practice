# STEP 1 — build the jar
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -B dependency:resolve

COPY src ./src
RUN mvn -q -e -B package -DskipTests

# STEP 2 — run the app
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]