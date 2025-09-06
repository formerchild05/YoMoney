package com.example.springtest.repository;

import com.example.springtest.model.entity.Transaction;
import com.example.springtest.service.dto.ResponseTransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    Transaction findByTransactionIdAndUserId(Integer transactionId, Integer userId);

    List<Transaction> findAllByUserId(Integer userId);

    int countTransactionByUserId(Integer userId);

    Transaction save(Transaction transaction);

    void deleteByTransactionIdAndUserId(Integer transactionId, Integer userId);
}
