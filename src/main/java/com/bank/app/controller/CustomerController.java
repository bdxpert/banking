package com.bank.app.controller;

import com.bank.app.dto.AccountDTO;
import com.bank.app.entity.Customer;
import com.bank.app.dto.CustomerDTO;

import com.bank.app.exception.ApiRequestException;
import com.bank.app.service.AccountService;
import com.bank.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        Collection<CustomerDTO> customers;
        try {
            customers = customerService.getAllCustomers();
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable long customerId) {
        CustomerDTO customerDTO;
        try {
            customerDTO = customerService.getCustomer(customerId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Customer with id " + customerId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerId) {
        try {
            customerService.deleteCustomer(customerId);
        }  catch (NullPointerException e) {
            throw new ApiRequestException("Customer with id " + customerId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>("Customer deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable long customerId, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO result;
        try {
            result = customerService.updateCustomer(customerId, customerDTO);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Customer with id " + customerId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customerDTO) {
        CustomerDTO result;
        try {
            result = customerService.addCustomer(customerDTO);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<?> getCustomerAccounts(@PathVariable long customerId) {
        Collection<AccountDTO> accounts;
        try {
            accounts = accountService.getAllAccounts(customerId);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
