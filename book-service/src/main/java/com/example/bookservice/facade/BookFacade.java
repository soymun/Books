package com.example.bookservice.facade;


import com.example.bookservice.model.Book.BookDto;
import com.example.bookservice.model.Book.BookUpdateDto;
import com.example.bookservice.service.impl.BookServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookFacade {

    private final BookServiceImp bookServiceImp;

    public ResponseEntity<?> saveBook(BookDto bookDtoSave){

        if(bookDtoSave == null){
            throw new RuntimeException("Book can not save");
        }

        bookServiceImp.saveBook(bookDtoSave);

        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> updateBook(BookUpdateDto bookDto){
        if(bookDto == null){
            throw new RuntimeException("Book can not save");
        }

        return ResponseEntity.ok(bookServiceImp.updateBook(bookDto));
    }

    public ResponseEntity<?> deleteBook(Long id){
        if(id == null){
            throw new RuntimeException("Book not found");
        }

        bookServiceImp.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getBook(Long id){
        if(id == null){
            throw new RuntimeException("Book with this id, not found");
        }

        return ResponseEntity.ok(bookServiceImp.getBookById(id));
    }

    public ResponseEntity<?> getBookByName(String name){
        return ResponseEntity.ok(bookServiceImp.getBookByName(name));
    }

    public ResponseEntity<?> getBookByAuthorId(Long authorId){
        return ResponseEntity.ok(bookServiceImp.getBookByAuthorId(authorId));
    }
}
