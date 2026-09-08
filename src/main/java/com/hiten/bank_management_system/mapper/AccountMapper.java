package com.hiten.bank_management_system.mapper;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.dto.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponse toResponse(Account account){
        return new AccountResponse(account.getAccountId(), account.getCustomer().getCustomerId(), account.getAccountType(),
                account.getCurrentBalance(), account.getAccountStatus(), account.getCreatedAt()
        );
    }
}
