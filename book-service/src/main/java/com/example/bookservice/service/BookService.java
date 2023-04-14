package com.example.bookservice.service;

import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookUpdateDto;

import java.io.IOException;
import java.util.List;

public interface BookService {

    BookDto getBookById(Long id);

    BookDto updateBook(BookUpdateDto bookDto);

    void saveBook(BookDto bookDto) throws IOException;

    void deleteBook(Long id);

    List<BookDto> getBookByName(String bookName);

    List<BookDto> getBookByAuthorId(Long authorId);
}
