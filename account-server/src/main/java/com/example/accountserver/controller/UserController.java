package com.example.accountserver.controller;

import com.example.accountserver.model.User.AccountDto;
import com.example.accountserver.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final AccountServiceImpl accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getUserById(id));
    }

    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(accountService.updateUser(accountDto));
    }


    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        accountService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/account")
    public ResponseEntity<?> saveAccount(@RequestBody AccountDto accountDto){
        accountService.saveUser(accountDto);
        return ResponseEntity.status(201).build();
    }
}
