package com.bank.app.repository;

import com.bank.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByAccountNumber(long accountNumber);
//    public Account updateAccount(Account account);
}
