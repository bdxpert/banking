package com.bank.app.service.impl;

import com.bank.app.domain.*;
import com.bank.app.dto.AccountDTO;
import com.bank.app.dto.AccountTransactionDTO;
import com.bank.app.dto.adapter.AccountAdapter;
import com.bank.app.entity.Account;
import com.bank.app.entity.AccountEntry;
import com.bank.app.entity.Customer;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.CustomerRepository;
import com.bank.app.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl extends AccountSubject implements AccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    public AccountServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.addObserver(new EmailSender());
        this.addObserver(new SMSSender());
    }
    /*
    @Transactional
    public Account createAccount(long accountNumber, String customerName) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);

        Customer customer = new Customer(customerName);
        customer.setAccounts(Collections.singletonList(account));
        customerRepository.save(customer);
        return account;
    }
     */
    @Transactional
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setType(account.getType());
        account.setAccountEntries(accountDTO.getAccountEntries());

        Customer customer = new Customer(accountDTO.getAccountName());
        customer.setAccounts(Collections.singletonList(account));
        customerRepository.save(customer);
        return AccountAdapter.getAccountDTOFromAccount(account);
    }
    //long accountNumber, double amount
    @Transactional
    public Boolean deposit(AccountTransactionDTO accountDepositDTO) throws Exception {
        try {
            Account account = accountRepository.findByAccountNumber(accountDepositDTO.getAccountNumber());
            AccountEntry entry = new AccountEntry();

            entry.setDate(new Date());
            entry.setAmount(accountDepositDTO.getAmount());
            entry.setDescription("deposit");
            entry.setFromAccountNumber("");
            entry.setFromPersonName("");
            List<AccountEntry> entryList = account.getAccountEntries();
            entryList.add(entry);
            account.setAccountEntries(entryList);

            accountRepository.save(account);

            notifyObs(accountDepositDTO.getAccountNumber());
            return Boolean.TRUE;
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public AccountDTO getAccount(long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return AccountAdapter.getAccountDTOFromAccount(account);
    }

    public Collection<AccountDTO> getAllAccounts(long customerId) {
        return AccountAdapter.getAccountDTOListFromAccountList(customerRepository.findById(customerId).get().getAccounts());
    }
    //long accountNumber, double amount
    @Transactional
    public Boolean withdraw(AccountTransactionDTO accountTransactionDTO) throws Exception {
        try {
            Account account = accountRepository.findByAccountNumber(accountTransactionDTO.getAccountNumber());
            AccountEntry entry = new AccountEntry();

            entry.setDate(new Date());
            entry.setAmount(-accountTransactionDTO.getAmount());
            entry.setDescription("widthdraw");
            entry.setFromAccountNumber("");
            entry.setFromPersonName("");
            List<AccountEntry> entryList = account.getAccountEntries();
            entryList.add(entry);
            account.setAccountEntries(entryList);
            accountRepository.save(account);
            notifyObs(accountTransactionDTO.getAccountNumber());

            return Boolean.TRUE;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        //TODO
    }
    public Boolean deleteAccount(AccountDTO accountDTO){
        Account account = accountRepository.findByAccountNumber(accountDTO.getAccountNumber());
        if (account !=null) {
            accountRepository.deleteById(account.getAccountNumber());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
    public AccountDTO updateAccount(AccountDTO accountDTO){
        Account account = accountRepository.findByAccountNumber(accountDTO.getAccountNumber());
        if (account !=null) {
            accountRepository.deleteById(account.getAccountNumber());
            return AccountAdapter.getAccountDTOFromAccount(account);
        }
        return null;
    }
    /*
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        notifyObs(fromAccount.getAccountnumber());
        accountDAO.updateAccount(toAccount);
        notifyObs(toAccount.getAccountnumber());
    }
    */
}
