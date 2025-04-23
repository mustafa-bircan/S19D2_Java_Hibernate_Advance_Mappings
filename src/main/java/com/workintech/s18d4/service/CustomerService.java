package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer find(Long id);
    Customer delete(Long id);


    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}