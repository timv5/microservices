package com.microservice.customer.service.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetails {

    private Customer customer;
    private Bookstore bookstore;

}
