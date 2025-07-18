FROM openjdk:21

WORKDIR /app

COPY target/spring-library.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
