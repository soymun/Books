package com.example.library.Service.Imp;

import com.example.library.Dto.Response.Author.AuthorCreateUpdateDto;
import com.example.library.Dto.Response.Author.AuthorDto;
import com.example.library.Entity.Author;
import com.example.library.Entity.Author_;
import com.example.library.Mapping.AuthorMapper;
import com.example.library.Reposiroties.AuthorRepository;
import com.example.library.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
@Slf4j
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;
    @PersistenceContext
    EntityManager entityManager;

    public AuthorServiceImp(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto getAuthor(Long id) {
        log.info("Get Author in service started");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorDto> cq = cb.createQuery(AuthorDto.class);
        Root<Author> root = cq.from(Author.class);

        cq.where(cb.equal(root.get(Author_.ID), id));
        cq.multiselect(
                root.get(Author_.ID),
                root.get(Author_.NAME_AUTHOR),
                root.get(Author_.SURNAME_AUTHOR)
        );
        log.info("Get Author in service end");
        return entityManager.createQuery(cq).getSingleResult();
    }

    public AuthorDto getAuthor(String name, String surname) {
        log.info("Get Author in service started");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorDto> cq = cb.createQuery(AuthorDto.class);
        Root<Author> root = cq.from(Author.class);

        cq.where(cb.and(cb.equal(root.get(Author_.NAME_AUTHOR), name), cb.equal(root.get(Author_.SURNAME_AUTHOR), surname)));
        cq.multiselect(
                root.get(Author_.ID),
                root.get(Author_.NAME_AUTHOR),
                root.get(Author_.SURNAME_AUTHOR)
        );
        log.info("Get Author in service end");
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public AuthorDto updateAuthor(AuthorCreateUpdateDto authorDto) {
        log.info("Update author started");
        Author author = authorMapper.authorCreateUpdateDtoToAuthor(authorDto);

        AuthorDto authorDto1 = authorMapper.authorToAuthorDto(authorRepository.save(author));
        log.info("Update author end");
        return authorDto1;
    }

    @Override
    public List<AuthorDto> getAuthorByUserId(Long id) {
        log.info("Get Authors in service started");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorDto> cq = cb.createQuery(AuthorDto.class);
        Root<Author> root = cq.from(Author.class);

        cq.where(cb.equal(root.get(Author_.USER_AUTHOR_ID), id));
        cq.multiselect(
                root.get(Author_.ID),
                root.get(Author_.NAME_AUTHOR),
                root.get(Author_.SURNAME_AUTHOR)
        );
        log.info("Get Authors in service end");
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public AuthorDto saveAuthor(AuthorCreateUpdateDto authorCreateUpdateDto) {
        log.info("Update author started");
        Author author = authorMapper.authorCreateUpdateDtoToAuthor(authorCreateUpdateDto);

        AuthorDto authorDto1 = authorMapper.authorToAuthorDto(authorRepository.save(author));
        log.info("Update author end");
        return authorDto1;
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("Delete author started");
        authorRepository.deleteById(id);
        log.info("Delete author end");
    }
}
