package com.bank.trustbank.controller;

import com.bank.trustbank.dto.TransactionDTO;
import com.bank.trustbank.service.TransactionService;
import com.bank.trustbank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to create a new transaction
    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

    // Endpoint to get a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }
}
