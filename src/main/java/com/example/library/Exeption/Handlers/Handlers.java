package com.example.library.Exeption.Handlers;


import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Exeption.FindDoublesException;
import com.example.library.Exeption.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
@Slf4j
public class Handlers {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e){
        log.error("User or book not found");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadValues.class)
    public ResponseEntity<?> badValues(BadValues e){
        log.error("Values in input incorrect");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FindDoublesException .class)
    public ResponseEntity<?> findDoublesException(FindDoublesException e){
        log.error("User already exsist");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException e){
        log.error("User token invalid");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> expiredJwt(RuntimeException e){
        log.error("Runtime exception");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }
}
