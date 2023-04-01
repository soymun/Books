package com.example.library.Controllers;

import com.example.library.Dto.UserBook.UserBookCreateDto;
import com.example.library.Facade.UserBooksFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class UserBookController {

    private final UserBooksFacade userBooksFacade;

    @PostMapping("/userBooks")
    public ResponseEntity<?> saveUserBooks(@RequestBody UserBookCreateDto userBookCreateDto){
        return userBooksFacade.saveUserBooks(userBookCreateDto);
    }

    @GetMapping("/userBooks/{id}")
    public ResponseEntity<?> getUserBooksByUserId(@PathVariable Long id){
        return userBooksFacade.getUserBooksByUserId(id);
    }
}
