package com.example.bookservice.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoSave {

    private Long id;

    private Long authorId;

    private String name;

    private String about;

    private Long price;
}
