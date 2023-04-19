package com.example.authorservice.facade;


import com.example.authorservice.model.Author.AuthorCreateDto;
import com.example.authorservice.model.Author.AuthorUpdateDto;
import com.example.authorservice.service.impl.AuthorServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorFacade {

    private final AuthorServiceImp authorServiceImp;

    public ResponseEntity<?> getAuthor(Long id){
        if(id == null){
            throw new RuntimeException("Author with this id,was not found");
        }

        return ResponseEntity.ok(authorServiceImp.getAuthor(id));
    }

    public ResponseEntity<?> getAuthor(String name, String surname, String patronymic){
        if(name == null || surname == null){
            throw new RuntimeException("Author with this name and surname, not found");
        }

        return ResponseEntity.ok(authorServiceImp.getAuthor(name, surname, patronymic));
    }

    public ResponseEntity<?> updateAuthor(AuthorUpdateDto authorCreateUpdateDto){
        if(authorCreateUpdateDto == null){
            throw new RuntimeException("Author with this name and surname, not found");
        }

        return ResponseEntity.ok(authorServiceImp.updateAuthor(authorCreateUpdateDto));
    }

    public ResponseEntity<?> getAuthorByUserId(Long id){
        if(id == null){
            throw new RuntimeException("Author with this user id,was not found");
        }

        return ResponseEntity.ok(authorServiceImp.getAuthorByUserId(id));
    }

    public ResponseEntity<?> saveAuthor(AuthorCreateDto authorCreateUpdateDto){
        if(authorCreateUpdateDto == null){
            throw new RuntimeException("Author with this name and surname, not found");
        }
        authorServiceImp.saveAuthor(authorCreateUpdateDto);

        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> deleteAuthor(Long id){
        if(id == null){
            throw new RuntimeException("Author with this user id,was not found");
        }
        authorServiceImp.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
