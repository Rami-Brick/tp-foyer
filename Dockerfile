FROM openjdk:17-jdk-alpine
#RUN apt-get update && apt-get install -y curl
EXPOSE 8085
ADD target/tp-foyer-0.0.1.jar tp-foyer-0.0.1.jar
ENTRYPOINT ["java","-jar","tp-foyer-0.0.1.jar"]