FROM maven:3.8.5-openjdk-17 as builder
# Build dependency project

# Build main project
WORKDIR /ec-service-order/source
COPY ./ .
RUN mvn clean install

FROM openjdk:17 as runtime
COPY --from=builder /ec-service-order/source/target/*.jar /ec-service-order/app.jar
CMD ["java", "-jar", "/ec-service-order/app.jar"]
