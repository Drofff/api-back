FROM openjdk:11
COPY ./target/api-back-0.1.jar .
EXPOSE 8080/tcp
CMD ["java", "-jar", "api-back-0.1.jar"]