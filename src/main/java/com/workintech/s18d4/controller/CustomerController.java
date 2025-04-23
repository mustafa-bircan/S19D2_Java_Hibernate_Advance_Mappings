package com.workintech.s18d4.controller;


import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//testlerde endpoints customers degil customer
//ayrica application properties de workintech zaten belirtilmis base url icin o yuzden burdan kaldirdim
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customer -> new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(cust -> ResponseEntity.ok(new CustomerResponse(cust.getId(), cust.getEmail(), cust.getSalary())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        //burda createCustomer metodu yerine save metodunu kullandim ayni seyi yapmalarina ragmen nedense digeri hata verdi.
        //bu sebepten account controller icin de save, find, update, delete isimli metodlari kullanabilirsin
        Customer createdCustomer = customerService.save(customer);
        //ayni sekilde test 200 bekliyormus, testi de degistirebilirsin
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponse(createdCustomer.getId(), createdCustomer.getEmail(), createdCustomer.getSalary()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(new CustomerResponse(updatedCustomer.getId(), updatedCustomer.getEmail(), updatedCustomer.getSalary()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
