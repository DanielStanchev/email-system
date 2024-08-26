FROM amazoncorretto:21-alpine

WORKDIR /app

COPY rest/target/rest-0.0.1-SNAPSHOT.jar /app/email.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "/app/email.jar"]

#docker file for image creation