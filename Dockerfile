FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/demo-base-de-datos-0.0.1.jar
COPY ${JAR_FILE} app_api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_api.jar"]