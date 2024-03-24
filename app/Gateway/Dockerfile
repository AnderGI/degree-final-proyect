FROM openjdk:23-slim-bullseye
VOLUME /tmp
ADD ./target/Gateway-0.0.1-SNAPSHOT.jar ms-gateway.jar
ENTRYPOINT ["java","-jar","ms-gateway.jar"]
