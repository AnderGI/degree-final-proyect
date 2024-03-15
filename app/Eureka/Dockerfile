FROM openjdk:23-slim-bullseye
VOLUME /tmp
ADD ./target/Eureka-0.0.1-SNAPSHOT.jar eurekams.jar
ENTRYPOINT ["java","-jar","eurekams.jar"]
