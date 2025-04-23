package com.workintech.s18d4.controller;


import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //account yaratilirken customer da guncellenmesi gerekiyor o yuzden custome service de dependency olarak ekledim
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.findAll().stream()
                .map(account -> new AccountResponse(
                        account.getId(),
                        account.getAccountName(),
                        account.getMoneyAmount(),
                        account.getCustomer()))
                .collect(Collectors.toList());
    }



    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        Account account = accountService.find(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.save(account);
        //test 200 bekliyormus sen created 201 donmussun (dogrusu senin yaptigin istersen testi degis istersen 200 don bu sekilde)
        return ResponseEntity.status(HttpStatus.OK).body(new AccountResponse(createdAccount.getId(), createdAccount.getAccountName(), createdAccount.getMoneyAmount(), createdAccount.getCustomer()));
    }

    @PutMapping("/{customerId}")
    //endpointte parametre ismi neyse metod parametresinin ismi de ayni olmali -> customerId
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long customerId,@RequestBody Account account) {
        //iligli customer'i buluyorum sonra accounts'a gelen accounti ekliyorum
        Customer customer = customerService.find(customerId);
        customer.getAccounts().add(account);

        //account'in customerini da id'sinden buldugum customer yapiyorum
        account.setCustomer(customer);

        Account updatedAccount = accountService.save(account);
        return ResponseEntity.ok(new AccountResponse(updatedAccount.getId(), updatedAccount.getAccountName(), updatedAccount.getMoneyAmount(), updatedAccount.getCustomer()));
    }

    //by metod account response donmesi bekleniyor o yuzden delete result'i account a atadim
    //sonra normal account response olusturup dondum
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountResponse> deleteAccount(@PathVariable Long id) {
        Account account = accountService.delete(id);
        //noContent 204 doner, test 200 bekliyor
        return ResponseEntity.ok(new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer()));
    }
}