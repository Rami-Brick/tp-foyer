FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/tp-foyer-0.0.1.jar tp-foyer-0.0.1.jar
ENTRYPOINT ["java","-jar","tp-foyer-0.0.1.jar"]