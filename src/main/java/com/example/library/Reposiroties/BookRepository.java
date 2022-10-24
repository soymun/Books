package com.example.library.Reposiroties;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookById(Long id);
}
