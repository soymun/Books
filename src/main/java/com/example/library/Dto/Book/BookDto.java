package com.example.library.Dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String authorName;

    private String authorSurname;

    private String name;

    private String about;

    private Long price;

    private String urlToPdf;
}
