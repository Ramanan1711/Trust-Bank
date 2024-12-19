package com.bank.trustbank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
    private String transactionType;  // Add this field for the type of transaction
    private LocalDateTime transactionDate;

    // Getters and Setters
    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;  // Getter for transactionType
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;  // Setter for transactionType
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
