package com.bank.trustbank.service;

import com.bank.trustbank.model.Account;
import com.bank.trustbank.repository.AccountRepository;
import com.bank.trustbank.dto.AccountDTO;
import com.bank.trustbank.model.User;
import com.bank.trustbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;  // Add UserRepository to fetch User by ID

    // Method to create a new account from AccountDTO
    public Account createAccount(AccountDTO accountDTO) {
        // Convert AccountDTO to Account entity
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());

        // Fetch the User based on the userId from DTO
        Optional<User> user = userRepository.findById(accountDTO.getUserId());
        if (user.isPresent()) {
            account.setUser(user.get());
        } else {
            throw new RuntimeException("User not found");
        }

        // Convert balance to BigDecimal
        account.setBalance(accountDTO.getBalance());

        return accountRepository.save(account);
    }

    // Method to get all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Method to get an account by ID
    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
