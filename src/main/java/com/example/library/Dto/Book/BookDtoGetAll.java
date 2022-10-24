package com.example.library.Dto.Book;

import lombok.Data;

@Data
public class BookDtoGetAll {
    private Long id;

    private String authorName;

    private String authorSurname;

    private String name;

    private String about;

    private Long price;
}
