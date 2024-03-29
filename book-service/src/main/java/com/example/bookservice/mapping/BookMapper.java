package com.example.bookservice.mapping;

import com.example.bookservice.entity.Book;
import com.example.bookservice.model.Author.Author;
import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookDtoSave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDtoSave bookDtoSave);
}
