package com.example.authorservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "name")
    private String nameAuthor;

    @Column(name = "surname")
    private String surnameAuthor;

    @Column(name = "patronymic")
    private String patronymicAuthor;

    private String urlToPhoto;
}
