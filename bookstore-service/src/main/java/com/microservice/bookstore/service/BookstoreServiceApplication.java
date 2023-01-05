package com.microservice.bookstore.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServiceApplication.class, args);
	}

}
