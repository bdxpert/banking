package com.bank.app.dto.adapter;

import com.bank.app.dto.AccountDTO;
import com.bank.app.dto.CustomerDTO;
import com.bank.app.entity.Account;
import com.bank.app.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class AccountAdapter {
    public static AccountDTO getAccountDTOFromAccount(Account account) {
        if(account == null)
            return null;
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountEntries(account.getAccountEntries());

        return accountDTO;
    }

    public static Account getAccountFromAccountDTO(AccountDTO accountDTO) {
        if(accountDTO == null) return null;
        Account account = new Account();

        account.setId(accountDTO.getId());
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountEntries(accountDTO.getAccountEntries());

        return account;
    }

    public static List<AccountDTO> getAccountDTOListFromAccountList (List<Account> accounts) {
        if(accounts == null) return null;
        return accounts.stream().map(account -> getAccountDTOFromAccount(account)).collect(Collectors.toList());
    }

    public static List<Account> getUserListFromUserDTOList (List<AccountDTO> accountDTOs) {
        if(accountDTOs == null) return null;
        return accountDTOs.stream().map(accountDTO -> getAccountFromAccountDTO(accountDTO)).collect(Collectors.toList());
    }
}
