 # Etapa de construcción
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

 # Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]