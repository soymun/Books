package com.example.library.Service.Imp;

import com.example.library.Dto.Basket.BasketDto;
import com.example.library.Dto.Basket.BasketSaveDto;
import com.example.library.Entity.*;
import com.example.library.Entity.Basket_;
import com.example.library.Entity.Book_;
import com.example.library.Reposiroties.BasketRepository;
import com.example.library.Service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketServiceImp implements BasketService {

    private final BasketRepository basketRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Long> getBasketsByUserId(Long id) {
        log.info("Get book on user with id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Basket> root = cq.from(Basket.class);
        Join<Basket, Book> join = root.join(Basket_.BOOK);

        cq.where(cb.equal(root.get(Basket_.USER_ID), id));

        cq.multiselect(
                join.get(Book_.ID)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public BasketDto getBasketById(Long id) {
        log.info("Get book with id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BasketDto> cq = cb.createQuery(BasketDto.class);
        Root<Basket> root = cq.from(Basket.class);

        cq.where(cb.equal(root.get(Basket_.USER_ID), id));

        cq.multiselect(
                root.get(Basket_.id),
                root.get(Basket_.bookId),
                root.get(Basket_.userId)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public void saveBasket(BasketSaveDto basketSaveDto) {
        log.info("Save basket");
        Basket basket = new Basket();
        basket.setUserId(basketSaveDto.getUserId());
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
