package com.example.bookservice.model.Book;

import com.example.bookservice.model.Author.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoSave {

    private Long authorId;

    private String name;

    private String about;

    private Long price;
}
