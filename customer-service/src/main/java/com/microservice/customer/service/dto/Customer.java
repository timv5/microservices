package com.microservice.customer.service.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    private String firstName;
    private String lastName;
    private Long bookstoreId;

}
