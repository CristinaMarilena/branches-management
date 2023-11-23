FROM gradle:7.3.3-jdk17 AS build
WORKDIR /workspace
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle clean build --no-daemon -x test

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
