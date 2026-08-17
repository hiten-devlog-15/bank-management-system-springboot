package com.hiten.bank_management_system.entity;

import com.hiten.bank_management_system.enums.TransactionType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", columnDefinition = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date_of_transaction")
    private LocalDate date;

    @Column(name = "balance_after_transaction")
    private double balanceAfterTransaction;
}
