package com.hiten.bank_management_system.controller;

import com.hiten.bank_management_system.dto.AccountRequest;
import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.mapper.AccountMapper;
import com.hiten.bank_management_system.dto.AccountResponse;
import com.hiten.bank_management_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId){
        Account account = accountService.getAccount(accountId);
        return accountMapper.toResponse(account);
    }

    @PostMapping
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest){
        Account account = accountService.createAccount(accountRequest.getCustomerId(), accountRequest.getAccountType(),
                accountRequest.getInitialDeposit());
        return accountMapper.toResponse(account);
    }

    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable Long accountId, @RequestParam double amount){
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable Long accountId, @RequestParam double amount, @RequestParam String password){
        accountService.withdraw(accountId, amount, password);
    }

    @PostMapping("/{senderAccountId}/transfer/{receiverAccountId}")
    public void transfer(@PathVariable Long senderAccountId, @PathVariable Long receiverAccountId,
                         @RequestParam double amount, @RequestParam String password) {
        accountService.transfer(senderAccountId, receiverAccountId, amount, password);
    }

    @PatchMapping("/{accountId}/close")
    public void closeAccount(@PathVariable Long accountId, @RequestParam String password){
        accountService.closeAccount(accountId, password);
    }
}
