package com.example.library.Dto.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateDto {

    private Long id;

    private Long userId;

    private String name;

    private String surname;

    private String patronymic;
}
