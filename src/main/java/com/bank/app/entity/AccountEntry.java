package com.bank.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountEntry {
    @Id
    @GeneratedValue
    private long id;

    private Date date;
    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;
}
