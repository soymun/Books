package com.example.library.Service.Imp;

import com.example.library.Dto.Basket.BasketSaveDto;
import com.example.library.Dto.Book.BookDtoGetAll;
import com.example.library.Entity.*;
import com.example.library.Reposiroties.BasketRepository;
import com.example.library.Service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Slf4j
public class BasketServiceImp implements BasketService {

    private final BasketRepository basketRepository;

    @PersistenceContext
    EntityManager entityManager;

    public BasketServiceImp(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public List<BookDtoGetAll> getAllByUserId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookDtoGetAll> cq = cb.createQuery(BookDtoGetAll.class);
        Root<Basket> root = cq.from(Basket.class);
        Join<Basket, Book> join = root.join(Basket_.BOOK);
        Join<Book, Author> join2 = join.join(Book_.AUTHOR);
        cq.where(cb.equal(root.get(Basket_.USER_ID), id));

        cq.multiselect(
                join.get(Book_.ID),
                join2.get(Author_.NAME_AUTHOR),
                join2.get(Author_.SURNAME_AUTHOR),
                join.get(Book_.NAME),
                join.get(Book_.ABOUT),
                join.get(Book_.PRICE)
        );
        log.info("Get book on user id");
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void saveBasket(BasketSaveDto basketSaveDto) {
        Basket basket = new Basket();
        basket.setUserId(basketSaveDto.getUserId());
        basket.setBookId(basketSaveDto.getBookId());
        basketRepository.save(basket);
        log.info("Save basket");
    }

    @Override
    public void deleteBasket(Long basketId) {
        log.info("Delete basket");
        basketRepository.deleteById(basketId);
    }
}
