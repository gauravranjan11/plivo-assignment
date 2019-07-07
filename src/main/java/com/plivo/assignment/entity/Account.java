package com.plivo.assignment.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="auth_id")
    private String authId;
    @Column(name="username")
    private String userName;
}
