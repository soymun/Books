package com.example.library.Reposiroties;

import com.example.library.Entity.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBooksRepository extends JpaRepository<UserBooks, Long> {

    List<UserBooks> getUserBooksByUserId(Long userId);
}
