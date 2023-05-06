package com.bank.app.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountFactory {
    Logger logger = LoggerFactory.getLogger(AccountFactory.class);

    private IAccountInterest accountInterest;

    public AccountFactory(String type) {
        if (type.equals("checking")) {
            accountInterest = new CheckingAccountInterest();
            logger.info("{} account is created", type);
        } else {
            accountInterest = new SavingsAccountInterest();
            logger.info("{} account is created", type);
        }
    }
    public IAccountInterest getInstance()
    {
        return accountInterest;
    }
}
