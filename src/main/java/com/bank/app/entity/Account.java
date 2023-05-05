package com.bank.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private long accountNumber;
    private String type; // savings or checking

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private List<AccountEntry> accountEntries;
}
