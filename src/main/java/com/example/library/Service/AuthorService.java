package com.example.library.Service;

import com.example.library.Dto.Author.AuthorCreateUpdateDto;
import com.example.library.Dto.Author.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto getAuthor(Long id);

    AuthorDto updateAuthor(AuthorCreateUpdateDto authorDto);

    List<AuthorDto> getAuthorByUserId(Long id);

    AuthorDto saveAuthor(AuthorCreateUpdateDto authorCreateUpdateDto);

    void deleteAuthor(Long id);
}
