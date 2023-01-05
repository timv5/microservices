package com.microservice.customer.service.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookstore {

    private Long bookstoreId;
    private String bookstoreName;
    private String bookstoreCode;

}
