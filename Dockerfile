FROM openjdk:8-jdk
COPY target/institution-checker-*.jar usr/src/institution-checker.jar
WORKDIR /usr/src/
ENTRYPOINT java -jar institution-checker.jar
