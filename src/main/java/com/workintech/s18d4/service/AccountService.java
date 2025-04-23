package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;
import java.util.Optional;


public interface AccountService {
    List<Account> findAll();
    Account save(Account account);
    Account find(Long id);
    Account delete(Long id);

    //testlerde bu isimlerde metod istedigi icin digerlerini sildim, ayni isi yapiyorlarmis zaten sadece isimler farkli
}