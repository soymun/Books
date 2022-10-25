package com.example.library.Dto.Author;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private Long id;

    private String nameAuthor;

    private String surnameAuthor;
}
