FROM openjdk:21
MAINTAINER sirdx
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms_customer.jar
ENTRYPOINT ["java", "-jar", "ms_customer.jar"]
