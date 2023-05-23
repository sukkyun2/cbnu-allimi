FROM openjdk:17-alpine
ARG APP_NAME
COPY target/${APP_NAME}.jar app.jar
# ARG PROFILE
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","app.jar"]

