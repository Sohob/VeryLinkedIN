FROM openjdk:18-alpine
#EXPOSE 8085
ARG JAR_FILE=target/*.jar
ARG YML_PATH=target/classes/application-docker.yml
COPY ${JAR_FILE} app.jar
COPY ${YML_PATH} application-docker.yml

ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=file:application-docker.yml"]

#FROM openjdk:18-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]