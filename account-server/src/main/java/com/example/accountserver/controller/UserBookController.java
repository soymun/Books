package com.example.accountserver.controller;

import com.example.accountserver.model.UserBook.UserBookCreateDto;
import com.example.accountserver.service.UserBooksServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserBookController {

    private final UserBooksServiceImpl userBooksFacade;

    @PostMapping("/userBooks")
    public ResponseEntity<?> saveUserBooks(@RequestBody UserBookCreateDto userBookCreateDto){
        userBooksFacade.saveUserBooks(userBookCreateDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/userBooks/{id}")
    public ResponseEntity<?> getUserBooksByUserId(@PathVariable Long id){
        return ResponseEntity.ok(userBooksFacade.getUserBooksByUserId(id));
    }
}
