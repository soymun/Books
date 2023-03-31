package com.example.library.Service;

import com.example.library.Dto.Author.AuthorCreateDto;
import com.example.library.Dto.Author.AuthorDto;
import com.example.library.Dto.Author.AuthorUpdateDto;

import java.util.List;

public interface AuthorService {

    AuthorDto getAuthor(Long id);

    AuthorDto updateAuthor(AuthorUpdateDto authorDto);

    List<AuthorDto> getAuthor(String name, String surname, String patronymic);

    AuthorDto getAuthorByUserId(Long id);

    void saveAuthor(AuthorCreateDto authorCreateUpdateDto);

    void deleteAuthor(Long id);
}
