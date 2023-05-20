FROM openjdk:17-alpine
ARG APP_NAME
COPY target/${APP_NAME}.jar app.jar
ARG ENVIRONMENT
CMD ["java","-Dspring.profiles.active=${ENVIRONMENT}","-jar","app.jar"]

