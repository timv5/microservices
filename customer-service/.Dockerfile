FROM maven:3.6.3-jdk-11-openj9 as maven_build

WORKDIR /app
COPY . /app
RUN mvn clean install

FROM openjdk:11
WORKDIR /app
COPY --from=maven_build app/target/customer-service-0.0.1-SNAPSHOT.jar customer-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "customer-service-0.0.1-SNAPSHOT.jar"]