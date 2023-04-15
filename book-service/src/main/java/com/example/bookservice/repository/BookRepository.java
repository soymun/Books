package com.example.bookservice.repository;

import com.example.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByName(String name);

    List<Book> getBooksByAuthorId(Long authorId);
}
