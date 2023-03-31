package com.example.library.Facade;


import com.example.library.Dto.Author.AuthorCreateDto;
import com.example.library.Dto.Author.AuthorUpdateDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Service.Imp.AuthorServiceImp;
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
            throw new BadValues("Author with this id,was not found");
        }

        return ResponseEntity.ok(authorServiceImp.getAuthor(id));
    }

    public ResponseEntity<?> getAuthor(String name, String surname, String patronymic){
        if(name == null || surname == null){
            throw new BadValues("Author with this name and surname, not found");
        }

        return ResponseEntity.ok(authorServiceImp.getAuthor(name, surname, patronymic));
    }

    public ResponseEntity<?> updateAuthor(AuthorUpdateDto authorCreateUpdateDto){
        if(authorCreateUpdateDto == null){
            throw new BadValues("Author with this name and surname, not found");
        }

        return ResponseEntity.ok(authorServiceImp.updateAuthor(authorCreateUpdateDto));
    }

    public ResponseEntity<?> getAuthorByUserId(Long id){
        if(id == null){
            throw new BadValues("Author with this user id,was not found");
        }

        return ResponseEntity.ok(authorServiceImp.getAuthorByUserId(id));
    }

    public ResponseEntity<?> saveAuthor(AuthorCreateDto authorCreateUpdateDto){
        if(authorCreateUpdateDto == null){
            throw new BadValues("Author with this name and surname, not found");
        }
        authorServiceImp.saveAuthor(authorCreateUpdateDto);

        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> deleteAuthor(Long id){
        if(id == null){
            throw new BadValues("Author with this user id,was not found");
        }
        authorServiceImp.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
