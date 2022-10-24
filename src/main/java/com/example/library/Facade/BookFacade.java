package com.example.library.Facade;


import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookDtoSave;
import com.example.library.Dto.Response.FactoryResponse.FactoryResponse;
import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Service.Imp.BookServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Slf4j
public class BookFacade {

    private final BookServiceImp bookServiceImp;

    private final FactoryResponse factoryResponse;

    @Autowired
    public BookFacade(BookServiceImp bookServiceImp, FactoryResponse factoryResponse) {
        this.bookServiceImp = bookServiceImp;
        this.factoryResponse = factoryResponse;
    }

    public ResponseEntity<?> saveBook(BookDtoSave bookDtoSave, MultipartFile multipartFile) throws IOException {
        if(bookDtoSave == null){
            log.debug("Book is null");
            throw new NotFoundException("Book with this name, can not save");
        }

        log.info("Start save book");
        BookDto bookDto = bookServiceImp.saveBook(bookDtoSave, multipartFile);
        log.info("End save book");
        return ResponseEntity.ok(factoryResponse.getResponse(bookDto));
    }

    public ResponseEntity<?> updateBook(BookDto bookDto){
        if(bookDto == null){
            log.debug("Book is null");
            throw new NotFoundException("Book with this id, can not save");
        }
        log.info("Start update book");
        BookDto bookDtoUpdate = bookServiceImp.updateBook(bookDto);
        log.info("End update book");
        return ResponseEntity.ok(factoryResponse.getResponse(bookDtoUpdate));
    }

    public ResponseEntity<?> deleteBook(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("Book with this id, not found");
        }
        log.info("Start delete book");
        bookServiceImp.deleteBook(id);
        log.info("End delete book");
        return ResponseEntity.ok(factoryResponse.getResponse("Delete suggest"));
    }

    public BookDto getBook(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("Book with this id, not found");
        }
        log.info("Start get book");
        BookDto bookDto = bookServiceImp.getBook(id);
        log.info("End get book");
        return bookDto;
    }
}
