package com.example.library.Service.Imp;

import com.example.library.Dto.Author.AuthorCreateDto;
import com.example.library.Dto.Author.AuthorDto;
import com.example.library.Dto.Author.AuthorUpdateDto;
import com.example.library.Entity.Author;
import com.example.library.Entity.Author_;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Mapping.AuthorMapper;
import com.example.library.Reposiroties.AuthorRepository;
import com.example.library.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public AuthorDto getAuthor(Long id) {
        log.info("Get Author with id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorDto> cq = cb.createQuery(AuthorDto.class);
        Root<Author> root = cq.from(Author.class);

        cq.where(cb.equal(root.get(Author_.ID), id));
        cq.multiselect(
                root.get(Author_.id),
                root.get(Author_.name),
                root.get(Author_.surname),
                root.get(Author_.patronymic),
                root.get(Author_.urlToPhoto)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<AuthorDto> getAuthor(String name, String surname, String patronymic) {
        log.info("Get Author with name {} and surname {} and patronymic {}", name, surname, patronymic);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorDto> cq = cb.createQuery(AuthorDto.class);
        Root<Author> root = cq.from(Author.class);

        cq.where(cb.and(cb.equal(root.get(Author_.patronymic), patronymic), cb.equal(root.get(Author_.name), name), cb.equal(root.get(Author_.surname), surname)));
        cq.multiselect(
                root.get(Author_.id),
                root.get(Author_.name),
                root.get(Author_.surname),
                root.get(Author_.patronymic),
                root.get(Author_.urlToPhoto)
        );

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorDto) {
        log.info("Update author with id {}", authorDto.getId());
        Author author = authorRepository.findById(authorDto.getId()).orElseThrow(() -> new NotFoundException("Автор не найден"));
        if(authorDto.getName() != null){
            author.setName(authorDto.getName());
        }

        if(authorDto.getSurname() != null){
            author.setSurname(authorDto.getSurname());
        }

        if(authorDto.getPatronymic() != null){
            author.setPatronymic(authorDto.getPatronymic());
        }

        if(authorDto.getUrlToPhoto() != null){
            author.setUrlToPhoto(authorDto.getUrlToPhoto());
        }
        return authorMapper.authorToAuthorDto(authorRepository.save(author));
    }

    @Override
    public AuthorDto getAuthorByUserId(Long id) {
        log.info("Get Authors by user id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorDto> cq = cb.createQuery(AuthorDto.class);
        Root<Author> root = cq.from(Author.class);

        cq.where(cb.equal(root.get(Author_.userId), id));
        cq.multiselect(
                root.get(Author_.id),
                root.get(Author_.name),
                root.get(Author_.surname),
                root.get(Author_.patronymic),
                root.get(Author_.urlToPhoto)
        );

        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    @Transactional
    public void saveAuthor(AuthorCreateDto authorCreateUpdateDto) {
        log.info("Update author started");

        authorRepository.save(authorMapper.authorCreateDtoToAuthor(authorCreateUpdateDto));
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        log.info("Delete author with id {}", id);
        authorRepository.deleteById(id);
    }
}
