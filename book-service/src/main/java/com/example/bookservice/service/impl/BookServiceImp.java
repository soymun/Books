package com.example.bookservice.service.impl;


import com.example.bookservice.entity.Book;
import com.example.bookservice.mapping.BookMapper;
import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookUpdateDto;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BookDto getBookById(Long id) {
        log.info("Find book with id {}", id);
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<BookDto> cq = cb.createQuery(BookDto.class);
//        Root<Book> root = cq.from(Book.class);
//
//        Join<Book, Author> join = root.join(Book_.AUTHOR);
//        cq.multiselect(
//                root.get(Book_.ID),
//                join.get(Author_.name),
//                join.get(Author_.surname),
//                root.get(Book_.NAME),
//                root.get(Book_.ABOUT),
//                root.get(Book_.PRICE),
//                root.get(Book_.URL_TO_PDF_FILE)
//        );
//        return entityManager.createQuery(cq).getSingleResult();
        return null;
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookDto) {
        log.info("Update book with id {}", bookDto.getId());
        Book book = bookRepository.findBookById(bookDto.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        if(bookDto.getName() != null) {
            book.setName(bookDto.getName());
        }
        if(bookDto.getAbout() != null) {
            book.setAbout(bookDto.getAbout());
        }
        if(bookDto.getPrice() != null) {
            book.setPrice(bookDto.getPrice());
        }
        if(bookDto.getAuthorId() != null){
            book.setAuthorId(bookDto.getAuthorId());
        }
        if(bookDto.getUrlToMainPhoto() != null){
            book.setUrlToMainPhoto(bookDto.getUrlToMainPhoto());
        }
        if(bookDto.getUrlToPdfFile() != null){
            book.setUrlToPdfFile(bookDto.getUrlToPdfFile());
        }
        return bookMapper.bookToBookDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void saveBook(BookDto bookDto) {
        log.info("Save book");
        bookRepository.save(bookMapper.bookDtoToBook(bookDto));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        log.info("Delete book with id {}", id);
        Book book = bookRepository.findBookById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
        deleteFile(book.getUrlToPdfFile());
        deleteFile(book.getUrlToMainPhoto());
    }

    @Override
    public List<BookDto> getBookByName(String bookName) {
        log.info("Get book by name {}", bookName);
        return bookRepository.getBooksByName(bookName).stream().map(bookMapper::bookToBookDto).toList();
    }

    @Override
    public List<BookDto> getBookByAuthorId(Long authorId) {
        log.info("Get book by author id {}", authorId);
        return bookRepository.getBooksByAuthorId(authorId).stream().map(bookMapper::bookToBookDto).toList();
    }

    public void deleteFile(String urlToFile){
        File file = new File(urlToFile);
        if (!file.delete()){
            throw new RuntimeException("File not delete");
        }
    }
}
