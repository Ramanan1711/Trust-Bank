package com.bank.trustbank.repository;

import com.bank.trustbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.fromAccount.id = :accountId OR t.toAccount.id = :accountId")
    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByTransactionType(String transactionType);
}
