package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;
import java.util.Optional;


public interface AccountService {
    List<Account> findAll();
    Account save(Account account);
    Account find(Long id);
    Account delete(Long id);

    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long id);
    Account createAccount(Account account);
    Account updateAccount(Long accountId, Account account);
    void deleteAccount(Long id);

}