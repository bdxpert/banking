package com.bank.app.dto;

import com.bank.app.entity.AccountEntry;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private long id;
    private long accountNumber;
    private String type; // savings or checking
    private List<AccountEntry> accountEntries;
    private String accountName;
}
