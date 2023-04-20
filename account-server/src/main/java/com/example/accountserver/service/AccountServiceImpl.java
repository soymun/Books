package com.example.accountserver.service;

import com.example.accountserver.mappers.AccountMapper;
import com.example.accountserver.model.User.AccountDto;
import com.example.accountserver.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    public void saveUser(AccountDto account) {
        log.info("Сохранение аккаунта");
        accountRepository.save(accountMapper.registrationToUser(account));
    }

    @Override
    public AccountDto updateUser(AccountDto userUpdateDto) {
        log.info("Изменение акунта с id {}", userUpdateDto.getId());
        return accountMapper.accountToAccountDto(accountRepository.save(accountMapper.registrationToUser(userUpdateDto)));
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Удаление акаунта с id {}", id);
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto getUserById(Long id) {
        return accountMapper.accountToAccountDto(accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Акаунт не найден")));
    }
}
