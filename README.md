# microservices
Simple Template application for microservice stack written in Java and Springboot.
It includes 2 microservices, service registry and api gateway.

## Technologies used
- Java 11
- Springboot
- Maven
- Docker, Docker compose
- in memory h2 database
- Eureka - service registry

## What is included in the project?
1. bookstore-service: api microservice
2. customer-service: api microservice
3. service-registry: registry listing all services
4. api-gateway: api gateway for bookstore-service and customer-service

## Additional functionality
Circuit breaker: spring-cloud-starter-circuitbreaker-resilience4j located in:
- bookstore-service
- customer-service

## bookstore-service:
### POST http://localhost:9001/bookstore/save
- request: {
  "bookstoreName": "bookstore#1_3",
  "bookstoreCode": "bookstore#2_3"
  }

### GET http://localhost:9001/bookstore/1
- response: {
  "bookstoreId": 1,
  "bookstoreName": "bookstore#1_3",
  "bookstoreCode": "bookstore#2_3"
  }

## customer-service
### POST http://localhost:9001/customer/save
- request: {
  "firstName": "lucy",
  "lastName": "white",
  "bookstoreId": 1
  }

### GET http://localhost:9002/customer/1
- response: {
  "customerId": 1,
  "firstName": "lucy",
  "lastName": "white",
  "bookstoreId": 1
  }

## service-registry
- Accessible on: http://localhost:8761/
- Lists all the services in the project and it's configs.

## api-gateway
Api gateway for bookstore-service and customer-service
- accessible on: http://localhost:9191
- example: http://localhost:9191/bookstore/1 -> returns bookstores, but goes through api-gateway not directly bookstore-service

#### Purpose? 
- Annotation: @EnableEurekaServer
- This Service will register every microservice (bookstore-service, customer-service) and then the client microservice
- will look up the Eureka server to get a dependent microservice to get the job done.This Eureka Server is owned by 
- Netflix and in this, Spring Cloud offers a declarative way to register and invoke services by Java annotation.


## How to run the app?
In /docker folder run the following command:
- docker-compose -f docker-compose up -d



