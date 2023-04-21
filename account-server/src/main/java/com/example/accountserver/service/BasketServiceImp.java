package com.example.accountserver.service;

import com.example.accountserver.mappers.BasketMapper;
import com.example.accountserver.model.Basket;
import com.example.accountserver.model.BasketDtos.BasketDto;
import com.example.accountserver.model.BasketDtos.BasketSaveDto;
import com.example.accountserver.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketServiceImp implements BasketService {

    private final BasketRepository basketRepository;

    private final BasketMapper basketMapper;

    @Override
    public List<Long> getBasketsByUserId(Long id) {
        log.info("Get book on user with id {}", id);
        return basketRepository.getBasketsByAccountId(id).stream().map(Basket::getId).toList();
    }

    @Override
    public BasketDto getBasketById(Long id) {
        log.info("Get book with id {}", id);
        return basketMapper.basketToBasketDto(basketRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найденна")));
    }

    @Override
    public void saveBasket(BasketSaveDto basketSaveDto) {
        log.info("Save basket");
        Basket basket = new Basket();
        basket.setAccountId(basketSaveDto.getAccountId());
        basket.setBookId(basketSaveDto.getBookId());
        basketRepository.save(basket);
    }

    @Override
    @Transactional
    public void deleteBasket(Long basketId) {
        log.info("Delete basket with id {}", basketId);
        basketRepository.deleteById(basketId);
    }
}
