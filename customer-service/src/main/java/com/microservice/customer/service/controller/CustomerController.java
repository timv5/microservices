package com.microservice.customer.service.controller;

import com.microservice.customer.service.dto.Customer;
import com.microservice.customer.service.dto.CustomerDetails;
import com.microservice.customer.service.entity.CustomerEntity;
import com.microservice.customer.service.service.CustomerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "customerFallback")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetails> getCustomer(@PathVariable("id") Long customerId) {
        LOGGER.info("Incoming request on getCustomer for {}", customerId);
        try {
            CustomerDetails customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                return ResponseEntity.status(HttpStatus.OK).body(customer);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "customerFallback")
    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        LOGGER.info("Incoming request on saveCustomer for {}", customer.toString());
        try {
            customerService.save(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> customerFallback(Exception e) {
        LOGGER.error("customerFallback");
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
