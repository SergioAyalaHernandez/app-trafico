 # Etapa de construcción
FROM gradle:jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

 # Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]