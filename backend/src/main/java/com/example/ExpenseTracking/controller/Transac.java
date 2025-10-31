package com.example.ExpenseTracking.controller;

import com.example.ExpenseTracking.service.TransService;

import com.example.ExpenseTracking.service.dto.TransDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/transaction")
@RestController
public class Transac {

    @Autowired
    TransService transService;

    @GetMapping()
    public ResponseEntity<?> transaction(Authentication authentication) {
        String username = authentication.getName();
        List<TransDTO> res = transService.getTransactions(username);

        log.info("get transactions for {}", username);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveTransaction(@RequestBody TransDTO transactionDTO, Authentication authentication) {
        String username = authentication.getName();
        try {
            TransDTO tr = transService.saveTransaction(transactionDTO, username);
            Map<String, Object> res = Map.of(
                    "transactionId", tr.getTransactionId().toString(),
                    "message", "Transaction created successfully",
                    "data", tr
            );
            log.info("user {} created transaction {}", username, tr.getTransactionId());
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            log.error("user {} failed create transaction : {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id,
            @RequestBody TransDTO transactionDTO, Authentication authentication) {
        String username = authentication.getName();
        try {
            transService.updateTransaction(transactionDTO, id);
            log.info("user {} updated transaction {}", username, transactionDTO.getTransactionId());
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Transaction updated successfully"));
        } catch (Exception e) {
            log.error("user {} failed update transaction : {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id, Authentication authentication) {
        try {
            transService.deleteTransaction(id);
            log.info("user {} deleted transaction {}", authentication.getName(), id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Transaction deleted successfully"));
        } catch (Exception e) {
            log.error("user {} failed delete transaction : {}", authentication.getName(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }
}
