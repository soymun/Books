package com.example.library.Service;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookUpdateDto;

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
