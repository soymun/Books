package com.example.bookservice.model.Book;

import com.example.bookservice.model.Author.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoGetAll {
    private Long id;

    private Author author;

    private String name;

    private String about;

    private Long price;
}
