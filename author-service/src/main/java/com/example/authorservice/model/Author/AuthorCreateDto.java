package com.example.authorservice.model.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateDto {

    private Long id;

    private Long userId;

    private String nameAuthor;

    private String surnameAuthor;

    private String patronymicAuthor;
}
