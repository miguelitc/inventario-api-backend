# Etapa 1: Descargar Maven y compilar el código
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear el entorno de Java ligero para correr la app
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el archivo .jar que se creó en la etapa 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Encendemos Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]