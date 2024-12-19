package com.bank.trustbank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDTO {

    private String accountNumber;  // Add accountNumber
    private Long userId;  // Add userId
    private BigDecimal balance;  // Change balance to BigDecimal
    private LocalDate dateCreated;

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
