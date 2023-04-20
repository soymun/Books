package com.example.accountserver.service;

import com.example.accountserver.model.User.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService{

    void saveUser(AccountDto account);

    AccountDto updateUser(AccountDto userUpdateDto);

    void deleteUser(Long id);

    AccountDto getUserById(Long id);
}
