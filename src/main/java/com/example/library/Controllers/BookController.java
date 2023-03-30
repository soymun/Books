package com.example.library.Controllers;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.Book.BookUpdateDto;
import com.example.library.Facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {

    private final BookFacade bookFacade;

    @PostMapping("/book")
    public ResponseEntity<?> saveBook(@RequestBody BookDto bookDto){
        return bookFacade.saveBook(bookDto);
    }

    @PutMapping("/book")
    public ResponseEntity<?> updateBook(@RequestBody BookUpdateDto bookDto){
        return bookFacade.updateBook(bookDto);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        return bookFacade.deleteBook(id);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getAboutBook(@PathVariable Long id){
        return bookFacade.getBook(id);
    }

    @GetMapping("/book")
    public ResponseEntity<?> getBook(@RequestParam(required = false) String bookName, @RequestParam(required = false) Long authorId){
        if(bookName != null){
            return bookFacade.getBookByName(bookName);
        }

        if(authorId != null){
            return bookFacade.getBookByAuthorId(authorId);
        }

        return ResponseEntity.noContent().build();
    }
}
