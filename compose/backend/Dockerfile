FROM openjdk:17-jdk-slim

# ./gradlew build
ARG JAR_FILE=./build/libs/*.jar

ADD ${JAR_FILE} app.jar

EXPOSE 13010

ENTRYPOINT ["java","-jar","/app.jar"]
