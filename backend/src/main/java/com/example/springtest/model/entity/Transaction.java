package com.example.springtest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "description")
    private String description = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CatType type;

    @Override
    public String toString() {
        return "transac";
    }
}
