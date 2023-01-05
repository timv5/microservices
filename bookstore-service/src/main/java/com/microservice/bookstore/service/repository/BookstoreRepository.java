package com.microservice.bookstore.service.repository;

import com.microservice.bookstore.service.entity.BookstoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookstoreRepository extends JpaRepository<BookstoreEntity, Long> {

    Optional<BookstoreEntity> findBookstoreEntityByBookstoreId(Long bookstoreId);

}
