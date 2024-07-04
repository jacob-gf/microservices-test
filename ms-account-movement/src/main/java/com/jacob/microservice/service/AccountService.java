package com.jacob.microservice.service;

import com.jacob.microservice.controller.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AccountService {

    List<AccountDto.Response> getAllAccounts();

    AccountDto.Response getAccountById(Long id);

    AccountDto.Response createAccount(AccountDto.Request request);

    AccountDto.Response updateAccount(Long id, AccountDto.Request request);

    void deleteAccount(Long id);
}
