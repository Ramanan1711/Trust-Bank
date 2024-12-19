package com.bank.trustbank.service;

import com.bank.trustbank.model.Account;
import com.bank.trustbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private AccountRepository accountRepository;

    public BigDecimal checkBalance(String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return account.getBalance();
        } else {
            throw new RuntimeException("Account not found.");
        }
    }
}
