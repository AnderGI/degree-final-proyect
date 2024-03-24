FROM openjdk:23-slim-bullseye
VOLUME /tmp
ADD ./target/cochesms-0.0.1-SNAPSHOT.jar ms-coches.jar
ENTRYPOINT ["java","-jar","ms-coches.jar"]
#CMD java -jar productos-ms.jar