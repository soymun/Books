package com.example.accountserver.service;

import com.example.accountserver.model.UserBook.UserBookCreateDto;
import com.example.accountserver.model.UserBook.UserBookDto;

import java.util.List;

public interface UserBooksService {

    void saveUserBooks(UserBookCreateDto userBookCreateDto);

    List<UserBookDto> getUserBooksByUserId(Long id);
}
