package com.example.library.Service;

import com.example.library.Dto.UserBook.UserBookCreateDto;
import com.example.library.Dto.UserBook.UserBookDto;

import java.util.List;

public interface UserBooksService {

    void saveUserBooks(UserBookCreateDto userBookCreateDto);

    List<UserBookDto> getUserBooksByUserId(Long id);
}
