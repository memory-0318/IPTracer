FROM maven:3-openjdk-8 AS build
RUN mkdir /projects
COPY . /projects
WORKDIR /projects
RUN mvn clean package

FROM openjdk:8-jre-alpine
RUN mkdir /app
COPY --from=build /projects/target/*.jar /app/application.jar
WORKDIR /app
EXPOSE 8080
CMD "java" "-jar" "application.jar"