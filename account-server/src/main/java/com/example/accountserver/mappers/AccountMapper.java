package com.example.accountserver.mappers;

import com.example.accountserver.model.Account;
import com.example.accountserver.model.User.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);

    Account registrationToUser(AccountDto accountDto);
}
