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
import java.util.Objects;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return  accountNumber == account.accountNumber
                && Objects.equals(type, account.type)
                && Objects.equals(interestStrategy, account.interestStrategy)
                && Objects.equals(accountEntries, account.accountEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountNumber,
                type, interestStrategy, accountEntries);
    }
}
