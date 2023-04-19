package com.example.authorservice.model.Author;

import lombok.Data;

@Data
public class AuthorUpdateDto {

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private String urlToPhoto;
}
