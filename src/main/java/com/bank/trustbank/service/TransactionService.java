package com.bank.trustbank.service;

import com.bank.trustbank.model.Account;
import com.bank.trustbank.model.Transaction;
import com.bank.trustbank.model.TransactionType; // Import the TransactionType enum
import com.bank.trustbank.repository.AccountRepository;
import com.bank.trustbank.repository.TransactionRepository;
import com.bank.trustbank.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Update to accept TransactionDTO
    public Transaction createTransaction(TransactionDTO transactionDTO) {
        // Convert TransactionDTO to Transaction
        Transaction transaction = new Transaction();

        // Convert the amount to BigDecimal for precision
        BigDecimal amount = BigDecimal.valueOf(transactionDTO.getAmount());
        transaction.setAmount(amount);

        // Set the transaction type using the enum
        // Assuming a simple logic, set the transaction type based on the logic in your DTO
        TransactionType transactionType = TransactionType.TRANSFER; // Use the enum here
        transaction.setTransactionType(transactionType);

        // Retrieve accounts by fromAccountId and toAccountId
        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDTO.getFromAccountId());
        Optional<Account> toAccountOptional = accountRepository.findById(transactionDTO.getToAccountId());

        if (fromAccountOptional.isPresent() && toAccountOptional.isPresent()) {
            transaction.setFromAccount(fromAccountOptional.get());
            transaction.setToAccount(toAccountOptional.get());
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("One or both accounts not found.");
        }
    }

    // Add method to get a transaction by ID
    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            return transactionOptional.get();
        } else {
            throw new RuntimeException("Transaction not found.");
        }
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public List<Transaction> getTransactionsByType(String transactionType) {
        return transactionRepository.findByTransactionType(transactionType);
    }
}
