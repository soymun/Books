package com.example.authorservice.mappers;


import com.example.authorservice.model.Author.AuthorCreateDto;
import com.example.authorservice.model.Author.AuthorDto;
import com.example.authorservice.model.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto authorToAuthorDto(Author author);

    Author authorCreateDtoToAuthor(AuthorCreateDto author);
}
