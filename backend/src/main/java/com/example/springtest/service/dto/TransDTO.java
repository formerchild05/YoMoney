package com.example.springtest.service.dto;

import com.example.springtest.model.entity.CatType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
