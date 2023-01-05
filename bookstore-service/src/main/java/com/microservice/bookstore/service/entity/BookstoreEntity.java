package com.microservice.bookstore.service.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookstoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookstoreId;
    private String bookstoreName;
    private String bookstoreCode;

}
