package com.example.accountserver.model.BasketDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketSaveDto {

    private Long accountId;

    private Long bookId;
}
