package com.example.accountserver.repository;

import com.example.accountserver.model.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBooksRepository extends JpaRepository<UserBooks, Long> {

    List<UserBooks> getUserBooksByAccountId(Long userId);
}
