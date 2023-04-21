package com.example.accountserver.model.UserBook;

import lombok.Data;

@Data
public class UserBookDto {

    private Long id;

    private Long accountId;

    private Long bookId;
}
