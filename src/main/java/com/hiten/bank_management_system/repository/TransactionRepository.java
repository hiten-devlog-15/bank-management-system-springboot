package com.hiten.bank_management_system.repository;

import com.hiten.bank_management_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
