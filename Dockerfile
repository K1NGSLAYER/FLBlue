FROM maven:3.6.3-openjdk-15 as build_stage
WORKDIR egala
COPY . .
RUN mvn package
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:12-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR egala
COPY --from=build_stage /egala/target .
EXPOSE 8080
ENTRYPOINT ["java", "-jar","rooftop-1.0.jar"]
