FROM openjdk:17-jdk-alpine

WORKDIR /comex

COPY target/*.jar /comex/app.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -Xmx512m -jar app.jar --server.port=$PORT