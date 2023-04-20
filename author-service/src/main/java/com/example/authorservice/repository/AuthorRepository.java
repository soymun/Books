package com.example.authorservice.repository;

import com.example.authorservice.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> getAuthorByAccountId(Long accountId);

    List<Author> getAuthorByNameAuthorAndSurnameAuthorAndPatronymicAuthor(String name, String surname, String patronymic);
}
