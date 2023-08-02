FROM openjdk:17-jdk-slim

ARG JAR_FILE=./build/libs/*.jar

ADD ${JAR_FILE} app.jar

EXPOSE 13010

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
