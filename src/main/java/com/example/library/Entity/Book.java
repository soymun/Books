package com.example.library.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
