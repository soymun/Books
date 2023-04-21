package com.example.accountserver.mappers;

import com.example.accountserver.model.Basket;
import com.example.accountserver.model.BasketDtos.BasketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    BasketDto basketToBasketDto(Basket basket);
}
