package com.example.fileservice.model.author;


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

    private String patronymicAuthor;

    private String urlToPhoto;
}
