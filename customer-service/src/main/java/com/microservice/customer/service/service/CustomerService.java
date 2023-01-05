package com.microservice.customer.service.service;

import com.microservice.customer.service.dto.Bookstore;
import com.microservice.customer.service.dto.Customer;
import com.microservice.customer.service.dto.CustomerDetails;
import com.microservice.customer.service.entity.CustomerEntity;
import com.microservice.customer.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public CustomerDetails getCustomerById(Long bookstoreId) {
        Optional<CustomerEntity> customerEntity = customerRepository.findCustomerEntityByCustomerId(bookstoreId);
        if (customerEntity.isEmpty()) {
            return null;
        }

        Customer customer = Customer.builder()
                .lastName(customerEntity.get().getLastName())
                .firstName(customerEntity.get().getFirstName())
                .bookstoreId(customerEntity.get().getBookstoreId())
                .build();
        Bookstore bookstore = restTemplate.getForObject("http://localhost:9001/bookstore/" + customer.getBookstoreId(), Bookstore.class);
        return CustomerDetails.builder()
                .bookstore(bookstore)
                .customer(customer)
                .build();
    }

    public void save(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .bookstoreId(customer.getBookstoreId())
                .build();
        customerRepository.save(customerEntity);
    }

}
