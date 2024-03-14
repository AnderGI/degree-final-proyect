FROM openjdk:23-slim-bullseye
VOLUME /tmp
ADD ./target/cochesms-0.0.1-SNAPSHOT.jar cochesms.jar
ENTRYPOINT ["java","-jar","cochesms.jar"]
#CMD java -jar productos-ms.jar