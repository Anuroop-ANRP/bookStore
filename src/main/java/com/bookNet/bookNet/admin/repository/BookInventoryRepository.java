package com.bookNet.bookNet.admin.repository;

import com.bookNet.bookNet.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookInventoryRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    // Custom query methods can be defined here if needed
    // For example, to find books by author name:
    // List<Book> findByAuthorName(String authorName);


}
