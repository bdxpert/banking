package com.bank.app.dto.adapter;

import com.bank.app.dto.CustomerDTO;
import com.bank.app.dto.UserDTO;
import com.bank.app.entity.BankUser;
import com.bank.app.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerAdapter {
    public static CustomerDTO getCustomerDTOFromCustomer(Customer customer) {
        if(customer == null)
            return null;
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setAccounts(customer.getAccounts());

        return customerDTO;
    }

    public static Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO) {
        if(customerDTO == null) return null;
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setAccounts(customerDTO.getAccounts());

        return customer;
    }

    public static List<CustomerDTO> getCustomerDTOListFromCustomerList (List<Customer> customers) {
        if(customers == null) return null;
        return customers.stream().map(customer -> getCustomerDTOFromCustomer(customer)).collect(Collectors.toList());
    }

    public static List<Customer> getUserListFromUserDTOList (List<CustomerDTO> customerDTOs) {
        if(customerDTOs == null) return null;
        return customerDTOs.stream().map(customerDTO -> getCustomerFromCustomerDTO(customerDTO)).collect(Collectors.toList());
    }
}
