package com.bank.app.domain;

public class CheckingAccountInterest implements IAccountInterest {
    @Override
    public double calculateInterest(double balance) {
        if (balance > 1000){
            return balance*0.025;
        }
        else{
            return balance*0.015;
        }
    }
}
