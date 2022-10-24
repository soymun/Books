package com.example.library.Dto.Book;

import lombok.Data;

@Data
public class BookDtoSave {

    private Long id;

    private Long authorId;

    private String name;

    private String about;

    private Long price;
}
