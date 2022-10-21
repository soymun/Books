package com.example.library.Entity;

import javax.persistence.*;

@Entity
@Table(name = "library_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private Role role;
}
