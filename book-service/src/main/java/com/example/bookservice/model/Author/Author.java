package com.example.bookservice.model.Author;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author extends RepresentationModel<Author> {

    private Long id;

    private String nameAuthor;

    private String surnameAuthor;

    private String patronymicAuthor;
}
