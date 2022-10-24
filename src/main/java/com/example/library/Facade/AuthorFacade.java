package com.example.library.Facade;


import com.example.library.Dto.Author.AuthorCreateUpdateDto;
import com.example.library.Dto.Response.FactoryResponse.FactoryResponse;
import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Service.Imp.AuthorServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorFacade {

    private final AuthorServiceImp authorServiceImp;

    private final FactoryResponse factoryResponse;
    @Autowired
    public AuthorFacade(AuthorServiceImp authorServiceImp, FactoryResponse factoryResponse) {
        this.authorServiceImp = authorServiceImp;
        this.factoryResponse = factoryResponse;
    }

    public ResponseEntity<?> getAuthor(Long id){
        if(id == null){
            log.debug("Id is not right");
            throw new BadValues("Author with this id,was not found");
        }
        log.info("Author find started");
        ResponseDto responseDto = factoryResponse.getResponse(authorServiceImp.getAuthor(id));
        log.info("Author find end");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> getAuthor(String name, String surname){
        if(name == null || surname == null){
            log.debug("Author can't find");
            throw new BadValues("Author with this name and surname, not found");
        }
        log.info("Author find started");
        ResponseDto responseDto = factoryResponse.getResponse(authorServiceImp.getAuthor(name, surname));
        log.info("Author find end");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> updateAuthor(AuthorCreateUpdateDto authorCreateUpdateDto){
        if(authorCreateUpdateDto == null){
            log.debug("Author can't find");
            throw new BadValues("Author with this name and surname, not found");
        }
        log.info("Author update is started");
        ResponseDto responseDto = factoryResponse.getResponse(authorServiceImp.updateAuthor(authorCreateUpdateDto));
        log.info("Author update is end");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> getAuthorByUserId(Long id){
        if(id == null){
            log.debug("Id is not right");
            throw new BadValues("Author with this user id,was not found");
        }
        log.info("Authors find started");
        ResponseDto responseDto = factoryResponse.getResponse(authorServiceImp.getAuthorByUserId(id));
        log.info("Authors find end");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> saveAuthor(AuthorCreateUpdateDto authorCreateUpdateDto){
        if(authorCreateUpdateDto == null){
            log.debug("Author can't find");
            throw new BadValues("Author with this name and surname, not found");
        }
        log.info("Author save is started");
        ResponseDto responseDto = factoryResponse.getResponse(authorServiceImp.saveAuthor(authorCreateUpdateDto));
        log.info("Author save is end");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> deleteAuthor(Long id){
        if(id == null){
            log.debug("Id is not right");
            throw new BadValues("Author with this user id,was not found");
        }
        log.info("Authors delete started");
        authorServiceImp.deleteAuthor(id);
        ResponseDto responseDto = factoryResponse.getResponse("Author delete, suggest");
        log.info("Authors delete end");
        return ResponseEntity.ok(responseDto);
    }
}
