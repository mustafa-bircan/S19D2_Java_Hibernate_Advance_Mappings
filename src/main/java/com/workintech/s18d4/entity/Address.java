package com.workintech.s18d4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private int no;
    private String city;
    private String country;

    @Column(name="description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    private Customer customer;
}
