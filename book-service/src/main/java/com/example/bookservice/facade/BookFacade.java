package com.example.bookservice.facade;


import com.example.bookservice.model.Author.Author;
import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookUpdateDto;
import com.example.bookservice.service.impl.BookServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookFacade {

    private final BookServiceImp bookServiceImp;

    @LoadBalanced
    private final RestTemplate restTemplate;

    public ResponseEntity<?> saveBook(BookDto bookDtoSave) {

        if (bookDtoSave == null) {
            throw new RuntimeException("Book can not save");
        }

        bookServiceImp.saveBook(bookDtoSave);

        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> updateBook(BookUpdateDto bookDto) {
        if (bookDto == null) {
            throw new RuntimeException("Book can not save");
        }

        BookDto updatedBook = bookServiceImp.updateBook(bookDto);

        updatedBook.setAuthor(getAuthor(updatedBook.getAuthor().getAuthorId()).getBody());

        return ResponseEntity.ok(updatedBook);
    }

    public ResponseEntity<?> deleteBook(Long id) {
        if (id == null) {
            throw new RuntimeException("Book not found");
        }

        bookServiceImp.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getBook(Long id) {
        if (id == null) {
            throw new RuntimeException("Book with this id, not found");
        }

        BookDto getBook = bookServiceImp.getBookById(id);

        getBook.setAuthor(getAuthor(getBook.getAuthor().getAuthorId()).getBody());

        return ResponseEntity.ok(getBook);
    }

    public ResponseEntity<?> getBookByName(String name) {
        return ResponseEntity.ok(bookServiceImp.getBookByName(name)
                .stream()
                .peek(bookDto -> bookDto.setAuthor(getAuthor(bookDto.getAuthor().getAuthorId()).getBody()))
                .toList());
    }

    public ResponseEntity<?> getBookByAuthorId(Long authorId) {
        return ResponseEntity.ok(bookServiceImp.getBookByAuthorId(authorId)
                .stream()
                .peek(bookDto -> bookDto.setAuthor(getAuthor(bookDto.getAuthor().getAuthorId()).getBody()))
                .toList());
    }

    private ResponseEntity<Author> getAuthor(Long authorId) {
        return restTemplate.exchange(
                "http://gateway:8072/authorservice/v1/author/{authorId}",
                HttpMethod.GET,
                null, Author.class, authorId);
    }
}
