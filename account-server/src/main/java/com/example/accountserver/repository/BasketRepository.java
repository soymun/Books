package com.example.accountserver.repository;

import com.example.accountserver.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> getBasketsByAccountId(Long accountId);
}
