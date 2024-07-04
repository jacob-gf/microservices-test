package com.jacob.microservice.service.impl;

import com.jacob.microservice.controller.dto.AccountDto;
import com.jacob.microservice.mapper.AccountMapper;
import com.jacob.microservice.repository.AccountRepository;
import com.jacob.microservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDto.Response> getAllAccounts() {
        return accountMapper.toAccountDtoList(accountRepository.findAll());
    }

    @Override
    public AccountDto.Response getAccountById(Long id) {
        return accountMapper.toAccountDto(accountRepository.findById(id).get());
    }

    @Override
    public AccountDto.Response createAccount(AccountDto.Request request) {
        return accountMapper.toAccountDto(accountRepository.save(accountMapper.toAccountEntity(request)));
    }

    @Override
    public AccountDto.Response updateAccount(Long id, AccountDto.Request request) {
        return accountMapper.toAccountDto(accountRepository.save(accountMapper.toAccountEntity(request)));
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
