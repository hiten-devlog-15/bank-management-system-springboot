package com.hiten.bank_management_system.repository;

import com.hiten.bank_management_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
