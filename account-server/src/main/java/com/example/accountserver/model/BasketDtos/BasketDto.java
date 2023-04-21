package com.example.accountserver.model.BasketDtos;

import lombok.Data;

@Data
public class BasketDto {
    private Long id;

    private Long accountId;

    private Long bookId;
}
