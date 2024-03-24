FROM openjdk:23-slim-bullseye
VOLUME /tmp
ADD ./target/Eureka-0.0.1-SNAPSHOT.jar ms-eureka.jar
ENTRYPOINT ["java","-jar","ms-eureka.jar"]
