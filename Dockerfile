FROM openjdk:17-alpine
ARG APP_NAME
COPY target/${APP_NAME}.jar app.jar
ARG PROFILE
ENV PROFILE = ${PROFILE}
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}","-jar","app.jar"]

