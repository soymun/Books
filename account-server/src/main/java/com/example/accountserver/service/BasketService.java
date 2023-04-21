package com.example.accountserver.service;

import com.example.accountserver.model.BasketDtos.BasketDto;
import com.example.accountserver.model.BasketDtos.BasketSaveDto;

import java.util.List;

public interface BasketService {

    List<Long> getBasketsByUserId(Long id);

    BasketDto getBasketById(Long id);

    void saveBasket(BasketSaveDto basketSaveDto);

    void deleteBasket(Long basketId);
}
