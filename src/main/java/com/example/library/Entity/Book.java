package com.example.library.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @OneToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    private String name;

    private String about;

    private Long price;

    private String urlToPdfFile;
}
