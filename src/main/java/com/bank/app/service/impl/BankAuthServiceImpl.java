package com.bank.app.service.impl;

import com.bank.app.auth.JWTHelper;
import com.bank.app.dto.AuthRequestDTO;
import com.bank.app.dto.LoginDTO;
import com.bank.app.dto.adapter.UserAdapter;
import com.bank.app.repository.UserRepository;
import com.bank.app.service.BankAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankAuthServiceImpl implements BankAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public LoginDTO login(AuthRequestDTO authRequestDTO) throws Exception{
        LoginDTO loginResponse;
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword())
            );
            loginResponse = generateAccessAndRefreshToken(authRequestDTO.getEmail());
        } catch (BadCredentialsException e) {
            log.info(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return loginResponse;
    }

    private LoginDTO generateAccessAndRefreshToken(String username) {
        return new LoginDTO(
                jwtHelper.generateAccessToken(username),
                UserAdapter.getUserDTOFromUser(userRepository.findByEmail(username))
        );
    }
}
