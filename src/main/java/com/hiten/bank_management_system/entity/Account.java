package com.hiten.bank_management_system.entity;

import com.hiten.bank_management_system.enums.AccountStatus;
import com.hiten.bank_management_system.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "accounts")
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", columnDefinition = "account_category")
    private AccountType accountType;

    @Column(name = "current_balance")
    private double currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", columnDefinition = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Account(Customer customer, AccountType accountType, double currentBalance,
                   AccountStatus accountStatus, LocalDate createdAt){
        this.customer = customer;
        this.accountType = accountType;
        this.currentBalance = currentBalance;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
    }
}
