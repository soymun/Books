package com.example.accountserver.mappers;

import com.example.accountserver.model.UserBook.UserBookCreateDto;
import com.example.accountserver.model.UserBook.UserBookDto;
import com.example.accountserver.model.UserBooks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserBooksMappers {

    UserBooks userBooksCreateDtoToUserBooks(UserBookCreateDto userBookCreateDto);

    UserBookDto userBooksToUserBooksDto(UserBooks userBooks);
}
