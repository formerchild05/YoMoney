package com.example.springtest.service.dto;

import com.example.springtest.model.entity.CatType;

import java.time.LocalDate;

public class TransDTO {
    Integer transactionId;
    CatType type;
    String categoryName;
    LocalDate createdAt;
    String description;
    double amount;

    public CatType getType() {
        return type;
    }

    public void setType(CatType type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
