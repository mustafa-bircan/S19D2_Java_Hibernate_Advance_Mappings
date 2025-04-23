package com.workintech.s18d4.controller;


import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts().stream()
                .map(account -> new AccountResponse(
                        account.getId(),
                        account.getAccountName(),
                        account.getMoneyAmount(),
                        account.getCustomer()))
                .collect(Collectors.toList());
    }



    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountById(id);
        return account.map(acc -> ResponseEntity.ok(new AccountResponse(acc.getId(), acc.getAccountName(), acc.getMoneyAmount(), acc.getCustomer())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountResponse(createdAccount.getId(), createdAccount.getAccountName(), createdAccount.getMoneyAmount(), createdAccount.getCustomer()));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id,@RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(id, account);
        return ResponseEntity.ok(new AccountResponse(updatedAccount.getId(), updatedAccount.getAccountName(), updatedAccount.getMoneyAmount(), updatedAccount.getCustomer()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}