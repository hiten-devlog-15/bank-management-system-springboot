package com.hiten.bank_management_system.controller;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId){
        return accountService.getAccount(accountId);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account.getCustomer().getCustomerId(), account.getAccountType(),
                account.getCurrentBalance());
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
