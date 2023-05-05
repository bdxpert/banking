package com.bank.app.repository;

import com.bank.app.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BankUser, Long> {
    public BankUser findByEmail(String email);
    public BankUser findById(long id);


}