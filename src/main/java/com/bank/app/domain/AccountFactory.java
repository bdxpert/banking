package com.bank.app.domain;

public class AccountFactory {
    private IAccountInterest accountInterest;

    public AccountFactory(String type) {
        if (type.equals("checking")) {
            accountInterest = new CheckingAccountInterest();
        } else {
            accountInterest = new SavingsAccountInterest();
        }
    }
    public IAccountInterest getInstance()
    {
        return accountInterest;
    }
}
