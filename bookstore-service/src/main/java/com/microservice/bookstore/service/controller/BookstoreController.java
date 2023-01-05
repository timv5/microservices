package com.microservice.bookstore.service.controller;

import com.microservice.bookstore.service.dto.Bookstore;
import com.microservice.bookstore.service.entity.BookstoreEntity;
import com.microservice.bookstore.service.service.BookstoreService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookstoreController.class);

    private final BookstoreService bookstoreService;

    @Autowired
    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "bookstoreFallback")
    @GetMapping("/{id}")
    public ResponseEntity<BookstoreEntity> getBookstore(@PathVariable("id") Long bookstoreId) throws Exception {
        LOGGER.info("Incoming request on getBookstore for {}", bookstoreId);
        BookstoreEntity bookstore = bookstoreService.getBookstoreById(bookstoreId);
        if (bookstore != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bookstore);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "bookstoreFallback")
    @PostMapping("/save")
    public ResponseEntity<?> saveBookstore(@RequestBody Bookstore bookstore) {
        LOGGER.info("Incoming request on saveBookstore for {}", bookstore.toString());
        try {
            bookstoreService.save(bookstore);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> bookstoreFallback(Exception e) {
        LOGGER.error("Exception message: {}, {}", e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
