package com.example.library.Service.Imp;

import com.example.library.Dto.UserBook.UserBookCreateDto;
import com.example.library.Dto.UserBook.UserBookDto;
import com.example.library.Mapping.UserBooksMappers;
import com.example.library.Reposiroties.UserBooksRepository;
import com.example.library.Service.UserBooksService;
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
        return userBooksRepository.getUserBooksByUserId(id).stream().map(userBooksMappers::userBooksToUserBooksDto).toList();
    }
}
