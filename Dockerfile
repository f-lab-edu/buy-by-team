FROM openjdk:11
ADD build/libs/bbt-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]