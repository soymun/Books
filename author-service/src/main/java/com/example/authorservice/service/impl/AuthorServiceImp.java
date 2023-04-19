package com.example.authorservice.service.impl;

import com.example.authorservice.mappers.AuthorMapper;
import com.example.authorservice.model.Author.AuthorCreateDto;
import com.example.authorservice.model.Author.AuthorDto;
import com.example.authorservice.model.Author.AuthorUpdateDto;
import com.example.authorservice.model.entity.Author;
import com.example.authorservice.repository.AuthorRepository;
import com.example.authorservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto getAuthor(Long id) {
        log.info("Get Author with id {}", id);

        return authorMapper.authorToAuthorDto(authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Автор не был найден")));
    }

    @Override
    public List<AuthorDto> getAuthor(String name, String surname, String patronymic) {
        log.info("Get Author with name {} and surname {} and patronymic {}", name, surname, patronymic);

        return authorRepository.getAuthorByNameAuthorAndSurnameAuthorAndPatronymicAuthor(name, surname, patronymic)
                .stream()
                .map(authorMapper::authorToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorDto) {
        log.info("Update author with id {}", authorDto.getId());
        Author author = authorRepository.findById(authorDto.getId()).orElseThrow(() -> new RuntimeException("Автор не найден"));
        if (authorDto.getName() != null) {
            author.setNameAuthor(authorDto.getName());
        }

        if (authorDto.getSurname() != null) {
            author.setSurnameAuthor(authorDto.getSurname());
        }

        if (authorDto.getPatronymic() != null) {
            author.setPatronymicAuthor(authorDto.getPatronymic());
        }

        if (authorDto.getUrlToPhoto() != null) {
            author.setUrlToPhoto(authorDto.getUrlToPhoto());
        }
        return authorMapper.authorToAuthorDto(authorRepository.save(author));
    }

    @Override
    public AuthorDto getAuthorByUserId(Long id) {
        log.info("Get Authors by user id {}", id);

        return authorMapper.authorToAuthorDto(authorRepository.getAuthorByUserId(id).orElseThrow(() -> new RuntimeException("Автор не был найден")));
    }

    @Override
    @Transactional
    public void saveAuthor(AuthorCreateDto authorCreateUpdateDto) {
        log.info("Save author");
        authorRepository.save(authorMapper.authorCreateDtoToAuthor(authorCreateUpdateDto));
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        log.info("Delete author with id {}", id);
        authorRepository.deleteById(id);
    }
}
