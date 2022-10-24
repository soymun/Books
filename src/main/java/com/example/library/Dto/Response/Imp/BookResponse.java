package com.example.library.Dto.Response.Imp;

import com.example.library.Dto.Response.Response;
import lombok.Data;

@Data
public class BookResponse implements Response {

    private Long id;

    private String authorName;

    private String authorSurname;

    private String name;

    private String about;

    private Long price;
}
