package com.bank.app.service;

import com.bank.app.entity.Customer;
import com.bank.app.dto.CustomerDTO;

import java.util.Collection;

public interface CustomerService {
    public Collection<CustomerDTO> getAllCustomers() throws Exception;
    public CustomerDTO getCustomer(long customerId);
    public CustomerDTO updateCustomer(long customerId, CustomerDTO customerDTO) throws Exception;
    public void deleteCustomer(long customerId);
    public CustomerDTO addCustomer(Customer customerDTO);
}
