package com.bank.trustbank.controller;

import com.bank.trustbank.dto.AccountDTO;
import com.bank.trustbank.service.AccountService;
import com.bank.trustbank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Endpoint to create a new account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(account);
    }

    // Endpoint to get all accounts
    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Endpoint to get an account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }
}
