package com.example.springtest.service.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * use for calculate transaction
 */
public class ResponseTransactionDTO {
    List<TransDTO> transList = new ArrayList<>();
    double income;
    double expense;
    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TransDTO> getTransList() {
        return transList;
    }

    public void setTransList(List<TransDTO> transList) {
        this.transList = transList;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public void pushList(TransDTO disTrans) {
        transList.add(disTrans);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "transList=" + transList +
                ", income=" + income +
                ", expense=" + expense +
                '}';
    }
}
