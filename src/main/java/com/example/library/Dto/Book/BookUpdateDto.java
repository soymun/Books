package com.example.library.Dto.Book;

import lombok.Data;

@Data
public class BookUpdateDto {

    private Long id;

    private Long authorId;

    private String name;

    private String about;

    private Long price;

    private String urlToPdfFile;

    private String urlToMainPhoto;
}
