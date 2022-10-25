package com.example.library.Service;

import com.example.library.Dto.Basket.BasketSaveDto;
import com.example.library.Dto.Book.BookDtoGetAll;

import java.util.List;

public interface BasketService {

    List<BookDtoGetAll> getAllByUserId(Long id);

    void saveBasket(BasketSaveDto basketSaveDto);

    void deleteBasket(Long basketId);
}
