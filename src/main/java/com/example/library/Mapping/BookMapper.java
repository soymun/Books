package com.example.library.Mapping;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDtoSave);
}
