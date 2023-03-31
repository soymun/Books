package com.example.library.Service;

import com.example.library.Dto.Basket.BasketDto;
import com.example.library.Dto.Basket.BasketSaveDto;

import java.util.List;

public interface BasketService {

    List<Long> getBasketsByUserId(Long id);

    BasketDto getBasketById(Long id);

    void saveBasket(BasketSaveDto basketSaveDto);

    void deleteBasket(Long basketId);
}
