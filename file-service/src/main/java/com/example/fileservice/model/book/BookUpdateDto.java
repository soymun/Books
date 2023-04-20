package com.example.fileservice.model.book;

import lombok.Data;

@Data
public class BookUpdateDto {

    private Long id;

    private String name;

    private String about;

    private Long price;

    private String urlToPdfFile;

    private String urlToMainPhoto;
}
