package com.example.bookservice.service.impl;


import com.example.bookservice.entity.Book;
import com.example.bookservice.mapping.BookMapper;
import com.example.bookservice.model.Author.Author;
import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookUpdateDto;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public BookDto getBookById(Long id) {
        log.info("Find book with id {}", id);

        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        BookDto bookDto = bookMapper.bookToBookDto(book);
        bookDto.setAuthor(getAuthor(book.getAuthorId()));

        return bookDto;
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookDto) {
        log.info("Update book with id {}", bookDto.getId());
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        if(bookDto.getName() != null) {
            book.setName(bookDto.getName());
        }
        if(bookDto.getAbout() != null) {
            book.setAbout(bookDto.getAbout());
        }
        if(bookDto.getPrice() != null) {
            book.setPrice(bookDto.getPrice());
        }
        if(bookDto.getUrlToMainPhoto() != null){
            book.setUrlToMainPhoto(bookDto.getUrlToMainPhoto());
        }
        if(bookDto.getUrlToPdfFile() != null){
            book.setUrlToPdfFile(bookDto.getUrlToPdfFile());
        }

        Book updatedBook = bookRepository.save(book);
        BookDto updatedBookDto = bookMapper.bookToBookDto(updatedBook);
        updatedBookDto.setAuthor(getAuthor(book.getAuthorId()));

        return updatedBookDto;
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
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }

    @Override
    public List<BookDto> getBookByName(String bookName) {
        log.info("Get book by name {}", bookName);
        return bookRepository.getBooksByName(bookName)
                .stream()
                .map(book -> {
                    BookDto bookDto = bookMapper.bookToBookDto(book);
                    bookDto.setAuthor(getAuthor(book.getAuthorId()));
                    return bookDto;
                })
                .toList();
    }

    @Override
    public List<BookDto> getBookByAuthorId(Long authorId) {
        log.info("Get book by author id {}", authorId);
        return bookRepository.getBooksByAuthorId(authorId)
                .stream()
                .map(book -> {
                    BookDto bookDto = bookMapper.bookToBookDto(book);
                    bookDto.setAuthor(getAuthor(book.getAuthorId()));
                    return bookDto;
                })
                .toList();
    }

    private Author getAuthor(Long authorId){
        Author author = new Author();
        author.setAuthorId(authorId);
        return author;
    }
}
