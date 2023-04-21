package com.example.accountserver.model.UserBook;

import lombok.Data;

@Data
public class UserBookCreateDto {

    private Long accountId;

    private Long basketId;

    private Long bookId;
}
