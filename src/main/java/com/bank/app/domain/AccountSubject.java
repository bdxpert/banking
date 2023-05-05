package com.bank.app.domain;

import java.util.ArrayList;
import java.util.List;

public class AccountSubject {
    List<IObserver> observerList = new ArrayList<>();
    public void addObserver(IObserver observer){
        observerList.add(observer);
    }
    public void notifyObs(long accountNo)
    {
        for (IObserver obser : observerList)
            obser.accountChange(accountNo);
    }
}
