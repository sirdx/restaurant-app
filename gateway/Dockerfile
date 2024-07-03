FROM openjdk:21
MAINTAINER sirdx
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms_gateway.jar
ENTRYPOINT ["java", "-jar", "ms_gateway.jar"]
