package com.workintech.s18d4.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private Double moneyAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    //sonsuz dongu olmasin diye ekledim
    // yoksa account objesinin icinde customer onun icinde tekrar account oyle gidiyor ve hata veriyor
    @JsonIgnore
    private Customer customer;
}