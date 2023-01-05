FROM maven:3.6.3-jdk-11-openj9 as maven_build

WORKDIR /app
COPY . /app
RUN mvn clean install

FROM openjdk:11
WORKDIR /app
COPY --from=maven_build app/target/bookstore-service-0.0.1-SNAPSHOT.jar bookstore-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "bookstore-service-0.0.1-SNAPSHOT.jar"]