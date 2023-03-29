package com.example.library.Service.Imp;


import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookDtoSave;
import com.example.library.Entity.Author;
import com.example.library.Entity.Author_;
import com.example.library.Entity.Book;
import com.example.library.Entity.Book_;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Mapping.BookMapper;
import com.example.library.Reposiroties.BookRepository;
import com.example.library.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public BookServiceImp(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto getBook(Long id) {
        log.info("Start find book with id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookDto> cq = cb.createQuery(BookDto.class);
        Root<Book> root = cq.from(Book.class);

        Join<Book, Author> join = root.join(Book_.AUTHOR);
        cq.multiselect(
                root.get(Book_.ID),
                join.get(Author_.name),
                join.get(Author_.surname),
                root.get(Book_.NAME),
                root.get(Book_.ABOUT),
                root.get(Book_.PRICE),
                root.get(Book_.URL_TO_PDF_FILE)
        );
        log.info("End find book with id {}", id);
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        log.info("Update book {}", bookDto);
        Book book = bookRepository.findBookById(bookDto.getId()).orElseThrow(() -> new NotFoundException("Book not found"));
        book.setName(bookDto.getName());
        book.setAbout(bookDto.getAbout());
        book.setPrice(bookDto.getPrice());
        Book bookUpdate = bookRepository.save(book);
        log.info("End update");
        return bookMapper.bookToBookDto(bookUpdate);
    }

    @Override
    public BookDto saveBook(BookDtoSave bookDto, MultipartFile file) throws IOException {
        log.info("Save book {}", bookDto);
        Book savedBook = bookMapper.bookDtoSaveToBook(bookDto);
        String url = "D:\\Users\\rcfef\\Library\\src\\main\\resources\\books\\";
        File file1 = new File(url + file.getOriginalFilename());
        if(file1.createNewFile()){
            file.transferTo(file1);
            savedBook.setUrlToPdfFile(file1.getAbsolutePath());
        }
        else {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(url + file.getOriginalFilename()));
            writer.write("");
            writer.flush();
            file.transferTo(file1);
            writer.close();
        }
        log.info("End save book");
        return bookMapper.bookToBookDto(bookRepository.save(savedBook));
    }

    @Override
    public void deleteBook(Long id) {
        log.info("Delete book with id {}", id);
        Book book = bookRepository.findBookById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        File file = new File(book.getUrlToPdfFile());
        if (!file.delete()){
            throw new RuntimeException("File not delete");
        }
        bookRepository.delete(book);
        log.info("End delete book");
    }
}
