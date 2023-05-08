package com.bank.app.service;

import com.bank.app.dto.AuthRequestDTO;
import com.bank.app.dto.LoginDTO;

public interface BankAuthService {
    LoginDTO login(AuthRequestDTO authRequestDTO) throws Exception;
}
