package com.bank.app.controller;

import com.bank.app.auth.JWTHelper;
import com.bank.app.dto.AuthRequestDTO;
import com.bank.app.dto.LoginDTO;
import com.bank.app.exception.ApiRequestException;
import com.bank.app.service.BankAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bank.app.utils.AppConstant.SUCCESS;

@RestController
@RequestMapping("/bankauth")
public class BankAuthController extends BaseController {
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private BankAuthService bankAuthService;
    @GetMapping
    public String getFirst() {
        return "Hello World!!";
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        LoginDTO loginDetailsDTO = null;
        try {
            loginDetailsDTO = bankAuthService.login(authRequestDTO);
        } catch (Exception e) {
            throw new ApiRequestException("Error occur while login!!", HttpStatus.FORBIDDEN);
        }
        return getResponse(SUCCESS, loginDetailsDTO, HttpStatus.OK);
    }
}
