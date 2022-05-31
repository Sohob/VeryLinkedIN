FROM openjdk:8-jre-alpine
EXPOSE 8085
ARG JAR_FILE=target/mypost-*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]