FROM eclipse-temurin:21-jdk

WORKDIR /app

# Match your actual Maven output filename
COPY target/scm2.0-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]