<<<<<<< HEAD
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
=======
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
>>>>>>> 38df84d (docker file)
ENTRYPOINT ["java", "-jar", "app.jar"]