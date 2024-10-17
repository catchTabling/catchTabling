FROM openjdk:17-jdk
LABEL maintainer="dh990921"

WORKDIR /app
ARG JAR_FILE=build/libs/catchtabling.jar
COPY ${JAR_FILE} /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]