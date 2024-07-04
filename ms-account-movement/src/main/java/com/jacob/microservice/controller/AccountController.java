package com.jacob.microservice.controller;

import com.jacob.microservice.controller.dto.AccountDto;
import com.jacob.microservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping
    public ResponseEntity<List<AccountDto.Response>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto.Response> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto.Response> createAccount(@RequestBody AccountDto.Request request){
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto.Response> updateAccount(@PathVariable Long id, @RequestBody  AccountDto.Request request){
        return new ResponseEntity<>(accountService.updateAccount(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        if (accountService.getAccountById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
