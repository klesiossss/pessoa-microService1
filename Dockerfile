FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service1.jar
ENTRYPOINT ["java","-jar","/service1.jar"]