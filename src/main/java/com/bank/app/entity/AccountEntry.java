package com.bank.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountEntry extends BaseEntity {

    private Date date;
    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountEntry that = (AccountEntry) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date) && Objects.equals(description, that.description) && Objects.equals(fromAccountNumber, that.fromAccountNumber) && Objects.equals(fromPersonName, that.fromPersonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, amount, description, fromAccountNumber, fromPersonName);
    }
}
