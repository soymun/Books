package com.example.library.Service;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookDtoGetAll;
import com.example.library.Dto.Book.BookDtoSave;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface BookService {

    BookDto getBook(Long id);

    BookDto updateBook(BookDto bookDto);

    BookDto saveBook(BookDtoSave bookDto, MultipartFile file) throws IOException;

    void deleteBook(Long id);
}
