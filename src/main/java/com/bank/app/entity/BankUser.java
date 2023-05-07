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
public class BankUser extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "bank_user_role")
    private List<Role> roles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BankUser bankUser = (BankUser) o;
        return  Objects.equals(firstName, bankUser.firstName)
                && Objects.equals(lastName, bankUser.lastName)
                && Objects.equals(email, bankUser.email)
                && Objects.equals(password, bankUser.password)
                && Objects.equals(roles, bankUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password, roles);
    }
}