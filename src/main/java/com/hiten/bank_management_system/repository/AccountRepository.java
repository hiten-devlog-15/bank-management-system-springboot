package com.hiten.bank_management_system.repository;

import com.hiten.bank_management_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomer_CustomerId(Long customerId);
}
