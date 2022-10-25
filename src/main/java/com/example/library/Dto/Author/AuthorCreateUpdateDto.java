package com.example.library.Dto.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateUpdateDto {

    private Long id;

    private Long userAuthorId;

    private String nameAuthor;

    private String surnameAuthor;
}
