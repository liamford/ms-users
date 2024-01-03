FROM openjdk:17-jdk-slim
COPY build/libs/ms-users-0.0.1-SNAPSHOT.jar ms-users.jar
ENTRYPOINT ["java","-jar","/ms-users.jar"]