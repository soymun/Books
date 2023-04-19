package com.example.bookservice.service;

import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookDtoSave;
import com.example.bookservice.model.Book.BookUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface BookService {

    BookDto getBookById(Long id);

    BookDto updateBook(BookUpdateDto bookDto);

    @Transactional
    void saveBook(BookDtoSave bookDto);

    void deleteBook(Long id);

    List<BookDto> getBookByName(String bookName);

    List<BookDto> getBookByAuthorId(Long authorId);
}
