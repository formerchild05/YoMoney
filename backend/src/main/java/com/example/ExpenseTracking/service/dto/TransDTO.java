package com.example.ExpenseTracking.service.dto;

import com.example.ExpenseTracking.model.entity.CatType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TransDTO {
    Long transactionId;
    String transId;
    CatType type;
    String categoryName;
    LocalDate createdAt;
    String description;
    double amount;


}
