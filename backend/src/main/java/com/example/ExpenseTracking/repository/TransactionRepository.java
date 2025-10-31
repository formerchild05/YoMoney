package com.example.ExpenseTracking.repository;

import com.example.ExpenseTracking.model.entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUser_Username(String username);


    Transaction findByTransactionId(Long transactionId);

    @Transactional
    void deleteByTransactionId(Long transactionId);
}
