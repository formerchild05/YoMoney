package com.example.springtest.service;

import com.example.springtest.model.entity.CatType;
import com.example.springtest.model.entity.Transaction;
import com.example.springtest.repository.TransactionRepository;
import com.example.springtest.repository.UserRepository;
import com.example.springtest.service.dto.TransDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public Transaction saveTransaction(TransDTO transaction, String username) {
        Transaction saveTransaction = mapTransactionDTOToTransaction(transaction);
//        save user into transaction then save through repository
        saveTransaction.setUser(userService.findUser(username));

        return transactionRepository.save(saveTransaction);
    }


    public Transaction updateTransaction(TransDTO transactionDTO, String username) {
        Transaction updateTransaction = mapTransactionDTOToTransaction(transactionDTO);

        updateTransaction.setUser(userService.findUser(username));
        return transactionRepository.save(updateTransaction);
    }

//    /**
//     * calculate all transaction by userId
//     * @param userId
//     * @return
//     */
//    public ResponseTransactionDTO getTransactionAllByUserId(Long userId) {
//        List<Transaction> list = transactionRepository.findAllByUserId(userId);
//        ResponseTransactionDTO responseTransactionDTO = new ResponseTransactionDTO();
//
//        responseTransactionDTO.setTotal(list.size());
//        double income = 0;
//        double expense = 0;
//
//        for (Transaction t : list) {
//            TransDTO d = new TransDTO();
//            d.setTransactionId(t.getTransactionId());
//            d.setCreatedAt(t.getCreatedAt().toLocalDate());
//            d.setDescription(t.getDescription());
//            d.setAmount(t.getAmount());
//            d.setType(t.getType());
//            d.setCategoryName(t.getCategoryName());
//            if (t.getType().equals(CatType.income)) {
//                income += t.getAmount();
//            } else {
//                expense += t.getAmount();
//            }
//            responseTransactionDTO.pushList(d);
//        }
//        responseTransactionDTO.setIncome(income);
//        responseTransactionDTO.setExpense(expense);
//        responseTransactionDTO.setTotal(list.size());
//        return responseTransactionDTO;
//    }

//    public TransDTO updateTransaction(Long userId, TransDTO transDTO) throws Exception {
//        Transaction transaction = transactionRepository.findByTransactionIdAndUserId(transDTO.getTransactionId(), userId);
//        if (transaction == null) {
//            throw new Exception("Transaction not found");
//        }
//        transaction.setType(transDTO.getType());
//        transaction.setAmount(transDTO.getAmount());
//        transaction.setDescription(transDTO.getDescription());
//        transaction.setCategoryName(transDTO.getCategoryName());
//
//        Transaction res = transactionRepository.save(transaction);
//
//        TransDTO d = new TransDTO();
//        d.setTransactionId(res.getTransactionId());
//        d.setCreatedAt(res.getCreatedAt().toLocalDate());
//        d.setAmount(res.getAmount());
//        d.setType(res.getType());
//        d.setCategoryName(res.getCategoryName());
//        d.setDescription(res.getDescription());
//        return d;
//    }

//    public void createTransaction(Long userId, TransDTO transDTO) throws Exception {
//        if (transDTO.getAmount() < 0) {
//            throw new Exception("Unacceptable amount!");
//        }
//        Transaction tr = new Transaction();
//        tr.setDescription(transDTO.getDescription());
//        tr.setAmount(transDTO.getAmount());
//        tr.setType(transDTO.getType());
//        tr.setCategoryName(transDTO.getCategoryName());
////        tr.setUserId(userId);
//        transactionRepository.save(tr);
//    }
//
//    public void deleteTransaction(Long transactionId) {
//        transactionRepository.deleteById(transactionId);
//    }
}
