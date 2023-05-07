package com.bank.app.controller;

import com.bank.app.dto.AccountDTO;
import com.bank.app.dto.AccountTransactionDTO;
import com.bank.app.exception.ApiRequestException;
import com.bank.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bank.app.utils.AppConstant.SUCCESS;

@RestController
@RequestMapping("accounts")
public class AccountController extends BaseController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable long accountId) {
        AccountDTO accountDTO;
        try {
            accountDTO = accountService.getAccount(accountId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Account with id " + accountId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, accountDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable long accountId) {
        try {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(accountId);
            accountService.deleteAccount(accountDTO);
        }  catch (NullPointerException e) {
            throw new ApiRequestException("Account with id " + accountId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, "Account deleted Successfully", HttpStatus.OK);
    }

//    @PutMapping("/{accountId}")
    @PutMapping
    public ResponseEntity<?> updateAccount(@PathVariable long accountId, @RequestBody AccountDTO accountDTO) {
        AccountDTO result;
        try {
            result = accountService.updateAccount(accountDTO);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Account with id " + accountId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTOPayload) {
        AccountDTO accountDTO;
        try {
            accountDTO = accountService.createAccount(accountDTOPayload);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, accountDTO, HttpStatus.OK);
    }
    @PostMapping("/deposit")
    public ResponseEntity<?> accountDeposit(@RequestBody AccountTransactionDTO accountTransactionDTO) {
        Boolean status;
        try {
              status = accountService.deposit(accountTransactionDTO);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, status? "Money deposited":"Somethings went wrong", HttpStatus.OK);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> accountWithdraw(@RequestBody AccountTransactionDTO accountTransactionDTO) {
        Boolean status;
        try {
            status = accountService.withdraw(accountTransactionDTO);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, status? "Money withdrawal":"Somethings went wrong", HttpStatus.OK);
    }
    @PostMapping("/add-interest")
    public ResponseEntity<?> accountInterest(@RequestBody AccountTransactionDTO accountTransactionDTO) {
        Boolean status;
        try {
            status = accountService.computeInterest(accountTransactionDTO);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return getResponse(SUCCESS, status? "Interest deposited":"Error to calculate interest", HttpStatus.OK);
    }

}
