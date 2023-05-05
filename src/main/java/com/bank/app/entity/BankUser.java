package com.bank.app.entity;

import jakarta.persistence.*;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class BankUser {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String email;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "bank_user_role")
    private List<Role> roles = new ArrayList<>();
}