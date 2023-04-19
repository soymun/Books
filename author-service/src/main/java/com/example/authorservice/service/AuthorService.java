package com.example.authorservice.service;

import com.example.authorservice.model.Author.AuthorCreateDto;
import com.example.authorservice.model.Author.AuthorDto;
import com.example.authorservice.model.Author.AuthorUpdateDto;

import java.util.List;

public interface AuthorService {

    AuthorDto getAuthor(Long id);

    AuthorDto updateAuthor(AuthorUpdateDto authorDto);

    List<AuthorDto> getAuthor(String name, String surname, String patronymic);

    AuthorDto getAuthorByUserId(Long id);

    void saveAuthor(AuthorCreateDto authorCreateUpdateDto);

    void deleteAuthor(Long id);
}
