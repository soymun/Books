package com.example.library.Mapping;

import com.example.library.Dto.UserBook.UserBookCreateDto;
import com.example.library.Dto.UserBook.UserBookDto;
import com.example.library.Entity.UserBooks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserBooksMappers {

    UserBooks userBooksCreateDtoToUserBooks(UserBookCreateDto userBookCreateDto);

    UserBookDto userBooksToUserBooksDto(UserBooks userBooks);
}
