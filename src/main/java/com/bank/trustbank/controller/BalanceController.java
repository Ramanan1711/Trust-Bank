package com.bank.trustbank.controller;

import com.bank.trustbank.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    // Endpoint to get balance by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber) {
        BigDecimal balance = balanceService.checkBalance(accountNumber);  // Call to checkBalance method
        return ResponseEntity.ok(balance);
    }
}
