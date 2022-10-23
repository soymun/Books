package com.example.library.Dto.Response.Author;

import lombok.Data;

@Data
public class AuthorCreateUpdateDto {

    private Long id;

    private Long userAuthorId;

    private String nameAuthor;

    private String surnameAuthor;
}
