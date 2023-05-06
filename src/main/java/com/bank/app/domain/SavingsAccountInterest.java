package com.bank.app.domain;

public class SavingsAccountInterest implements IAccountInterest {

    @Override
    public double calculateInterest(double balance) {
        if (balance > 5000){
            return balance*0.04;
        }
        else{
            if (balance > 1000){
                return balance*0.02;
            }
            else{
                return balance*0.01;
            }
        }
    }
}
