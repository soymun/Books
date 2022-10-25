package com.example.library.Dto.Basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketSaveDto {

    private Long userId;

    private Long bookId;
}
