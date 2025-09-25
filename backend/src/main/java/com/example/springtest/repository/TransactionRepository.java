package com.example.springtest.repository;

import com.example.springtest.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionRepository extends JpaRepository<Transaction, Long> {



}
