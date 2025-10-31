package com.example.ExpenseTracking.service;

import com.example.ExpenseTracking.model.entity.Transaction;
import com.example.ExpenseTracking.repository.TransactionRepository;
import com.example.ExpenseTracking.service.dto.TransDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TransService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    public Transaction mapTransactionDTOToTransaction(TransDTO transDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transDTO.getAmount());
        transaction.setType(transDTO.getType());
        transaction.setDescription(transDTO.getDescription());
        transaction.setCategoryName(transDTO.getCategoryName());
        return transaction;
    }

    public TransDTO mapTransactionToTransDTO(Transaction transaction) {
        TransDTO transDTO = new TransDTO();
        transDTO.setTransId(transaction.getTransactionId().toString());
        transDTO.setAmount(transaction.getAmount());
        transDTO.setType(transaction.getType());
        transDTO.setDescription(transaction.getDescription());
        transDTO.setCategoryName(transaction.getCategoryName());
        transDTO.setTransactionId(transaction.getTransactionId());
        transDTO.setCreatedAt(transaction.getCreatedAt());
        return transDTO;
    }

    public List<TransDTO> getTransactions(String username) {
        List<Transaction> result = transactionRepository.findAllByUser_Username(username);
        List<TransDTO> transDTOList = new ArrayList<>();
        for (Transaction transaction : result) {
            TransDTO transDTO = mapTransactionToTransDTO(transaction);
            transDTOList.add(transDTO);
        }
        return transDTOList;
    }

    public TransDTO saveTransaction(TransDTO transaction, String username) {
        Transaction saveTransaction = mapTransactionDTOToTransaction(transaction);
//        save user into transaction then save through repository
        saveTransaction.setUser(userService.findUser(username));

        Transaction savedTransaction = transactionRepository.save(saveTransaction);

        return mapTransactionToTransDTO(savedTransaction);
    }


    public Transaction updateTransaction(TransDTO transactionDTO, Long transactionId) {
        Transaction saveTransaction = transactionRepository.findByTransactionId(transactionId);

        saveTransaction.setAmount(transactionDTO.getAmount());
        saveTransaction.setType(transactionDTO.getType());
        saveTransaction.setDescription(transactionDTO.getDescription());
        saveTransaction.setCategoryName(transactionDTO.getCategoryName());
        saveTransaction.setCreatedAt(transactionDTO.getCreatedAt());

        return transactionRepository.save(saveTransaction);
    }

    public void deleteTransaction(Long transactionId) throws Exception {
        if (transactionRepository.findByTransactionId(transactionId) == null) {
            throw new Exception("Transaction not found");
        }
        transactionRepository.deleteByTransactionId(transactionId);
    }
}
