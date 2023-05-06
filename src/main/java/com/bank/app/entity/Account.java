package com.bank.app.entity;

import com.bank.app.domain.AccountFactory;
import com.bank.app.domain.CheckingAccountInterest;
import com.bank.app.domain.IAccountInterest;
import com.bank.app.domain.SavingsAccountInterest;
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
    @Transient
    private IAccountInterest interestStrategy;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private List<AccountEntry> accountEntries;

    public IAccountInterest getInterestStrategy()
    {
        AccountFactory accountFactory = new AccountFactory(this.getType());
        return accountFactory.getInstance();
    }

}
