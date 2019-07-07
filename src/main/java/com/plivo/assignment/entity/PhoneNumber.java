package com.plivo.assignment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="number")
    private String number;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

}
