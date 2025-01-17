FROM maven:3-eclipse-temurin-17 AS build
COPY . . 
RUN mvn clean package -DskipTests 

FROM eclipse-temurin:17-alpine
COPY --from=build /target/jobportal-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar" ]