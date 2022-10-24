package com.example.library.Controllers;


import com.example.library.Dto.Author.AuthorCreateUpdateDto;
import com.example.library.Facade.AuthorFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sibrary")
@Slf4j
public class AuthorController {

    private final AuthorFacade authorFacade;

    public AuthorController(AuthorFacade authorFacade) {
        this.authorFacade = authorFacade;
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id){
        log.info("Author get");
        return authorFacade.getAuthor(id);
    }

    @GetMapping("/author/{name}/{surname}")
    public ResponseEntity<?> getAuthorById(@PathVariable String name, @PathVariable String surname){
        log.info("Author get with name and surname");
        return authorFacade.getAuthor(name, surname);
    }

    @PutMapping("/author")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorCreateUpdateDto authorCreateUpdateDto){
        log.info("Author update");
        return authorFacade.updateAuthor(authorCreateUpdateDto);
    }

    @GetMapping("/author/user/{id}")
    public ResponseEntity<?> getAuthorsByUserId(@PathVariable Long id){
        log.info("Author get by user id");
        return authorFacade.getAuthorByUserId(id);
    }

    @PostMapping("/author")
    public ResponseEntity<?> saveAuthor(@RequestBody AuthorCreateUpdateDto authorCreateUpdateDto){
        log.info("Author save");
        return authorFacade.saveAuthor(authorCreateUpdateDto);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        log.info("Author delete");
        return authorFacade.deleteAuthor(id);
    }
}
