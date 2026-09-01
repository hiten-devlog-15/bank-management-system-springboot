package com.hiten.bank_management_system.repository;

import com.hiten.bank_management_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountId(Long accountId);
}
