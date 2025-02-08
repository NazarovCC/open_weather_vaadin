FROM amazoncorretto:17-alpine-jdk

COPY open_weather-0.0.1-SNAPSHOT.jar .
EXPOSE 8087

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "open_weather-0.0.1-SNAPSHOT.jar"]