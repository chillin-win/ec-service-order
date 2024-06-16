FROM maven:3.6.3-openjdk-11 as builder
# Build dependency project
WORKDIR /core/source
COPY ./core .
RUN mvn clean install

# Build main project
WORKDIR /spring-event/source
COPY ./spring-event .
RUN mvn clean install

FROM openjdk:11.0.12-jdk as runtime
COPY --from=builder /spring-event/source/target/*.jar /spring-event/app.jar
CMD ["java", "-jar", "/spring-event/app.jar"]
