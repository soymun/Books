package com.example.accountserver.service;

import com.example.accountserver.mappers.UserBooksMappers;
import com.example.accountserver.model.UserBook.UserBookCreateDto;
import com.example.accountserver.model.UserBook.UserBookDto;
import com.example.accountserver.repository.UserBooksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserBooksServiceImpl implements UserBooksService {

    private final UserBooksRepository userBooksRepository;

    private final UserBooksMappers userBooksMappers;

    @Override
    public void saveUserBooks(UserBookCreateDto userBookCreateDto) {
        userBooksRepository.save(userBooksMappers.userBooksCreateDtoToUserBooks(userBookCreateDto));
    }

    @Override
    public List<UserBookDto> getUserBooksByUserId(Long id) {
        return userBooksRepository.getUserBooksByAccountId(id).stream().map(userBooksMappers::userBooksToUserBooksDto).toList();
    }
}
