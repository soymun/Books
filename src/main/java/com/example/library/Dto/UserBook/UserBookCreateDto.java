package com.example.library.Dto.UserBook;

import lombok.Data;

@Data
public class UserBookCreateDto {

    private Long userId;

    private Long basketId;

    private Long bookId;
}
