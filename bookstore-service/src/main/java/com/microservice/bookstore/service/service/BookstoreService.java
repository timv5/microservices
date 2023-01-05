package com.microservice.bookstore.service.service;

import com.microservice.bookstore.service.dto.Bookstore;
import com.microservice.bookstore.service.entity.BookstoreEntity;
import com.microservice.bookstore.service.repository.BookstoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookstoreService {

    private final BookstoreRepository bookstoreRepository;

    @Autowired
    public BookstoreService(BookstoreRepository bookstoreRepository) {
        this.bookstoreRepository = bookstoreRepository;
    }

    public BookstoreEntity getBookstoreById(Long bookstoreId) {
        Optional<BookstoreEntity> bookstore = bookstoreRepository.findBookstoreEntityByBookstoreId(bookstoreId);
        return bookstore.orElse(null);
    }

    public void save(Bookstore bookstore) {
        BookstoreEntity bookstoreEntity = BookstoreEntity.builder()
                .bookstoreCode(bookstore.getBookstoreCode())
                .bookstoreName(bookstore.getBookstoreName())
                .build();
        bookstoreRepository.save(bookstoreEntity);
    }

}
