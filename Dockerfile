FROM openjdk:18-jre-slim

WORKDIR /comex

COPY target/*.jar /comex/app.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -jar app.jar