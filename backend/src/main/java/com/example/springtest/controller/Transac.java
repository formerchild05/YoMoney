package com.example.springtest.controller;

import com.example.springtest.service.TransService;
import com.example.springtest.service.dto.ResponseTransactionDTO;
import com.example.springtest.service.dto.TransDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/transaction")
@RestController
public class Transac {

    @Autowired
    TransService transService;



    @PostMapping("/save")
    public ResponseEntity<?> saveTransaction(@RequestBody TransDTO transactionDTO, Authentication authentication) {
        String username = authentication.getName();
        try {
            transService.saveTransaction(transactionDTO, username);
            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateTransaction(@RequestBody TransDTO transactionDTO, Authentication authentication) {
        String username = authentication.getName();
        try {
            transService.updateTransaction(transactionDTO, username);
            return ResponseEntity.status(HttpStatus.OK).body("Transaction updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


//    @GetMapping("/getTransactionAll/{user_id}")
//    public ResponseEntity<?> getTransaction(@PathVariable Long user_id) {
//        ResponseTransactionDTO res = transService.getTransactionAllByUserId(user_id);
//        return ResponseEntity.ok().body(res);
//    }
//
//    /**
//     * body requiered {
//     *     transaction ID:
//     *     type:
//     *     amount:
//     *     description:
//     *     categoryName:
//     * }
//     * @param user_id
//     * @param transDTO
//     * @return
//     */
//    @PutMapping("updateTransaction/{user_id}")
//    public ResponseEntity<?> updateTransaction(@PathVariable Long user_id,
//                                               @RequestBody TransDTO transDTO
//                                               ) {
//        try {
//            return ResponseEntity.ok().body(transService.updateTransaction(user_id, transDTO));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    /**
//     * body required {
//     *     description :
//     *     category name:
//     *     amount:
//     *     type:
//     * }
//     * @param user_id
//     * @param transDTO
//     * @return
//     */
//    @PostMapping("createTransaction/{user_id}")
//    public ResponseEntity<?> createTransaction(@PathVariable Long user_id,
//                                               @RequestBody TransDTO transDTO) {
//        try {
//            transService.createTransaction(user_id, transDTO);
//            return ResponseEntity.ok(HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    /**
//     * need transaction id to delete
//     * @param transaction_id
//     * @return
//     */
//    @DeleteMapping("deleteTransaction/{transaction_id}")
//    public ResponseEntity<?> deleteTransaction(@PathVariable Long transaction_id) {
//        transService.deleteTransaction(transaction_id);
//        return ResponseEntity.ok().body("delete Successfully");
//    }
}
