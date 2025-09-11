package com.example.springtest.repository;

import com.example.springtest.model.entity.Transaction;
import com.example.springtest.service.dto.ResponseTransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {



}
