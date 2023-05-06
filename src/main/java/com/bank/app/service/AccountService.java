package com.bank.app.service;

import com.bank.app.dto.AccountDTO;
import com.bank.app.dto.AccountTransactionDTO;
import com.bank.app.entity.Account;

import java.util.Collection;

public interface AccountService {
    //Account createAccount(long accountNumber, String customerName);
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccount(long accountNumber);
    Boolean deleteAccount(AccountDTO accountDTO);

    AccountDTO updateAccount(AccountDTO accountDTO);
    Collection<AccountDTO> getAllAccounts(long customerId);
//    void deposit (long accountNumber, double amount);
    Boolean deposit (AccountTransactionDTO accountTransactionDTO) throws Exception;
//    void withdraw (long accountNumber, double amount);
    Boolean withdraw (AccountTransactionDTO accountTransactionDTO) throws Exception;
    void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
    public Boolean computeInterest(AccountTransactionDTO accountTransactionDTO) throws Exception;
}
