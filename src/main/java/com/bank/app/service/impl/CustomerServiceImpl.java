package com.bank.app.service.impl;

import com.bank.app.dto.CustomerDTO;
import com.bank.app.dto.adapter.CustomerAdapter;
import com.bank.app.entity.Customer;
import com.bank.app.repository.CustomerRepository;
import com.bank.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Collection<CustomerDTO> getAllCustomers() throws Exception {
        Collection<CustomerDTO> result = new ArrayList<>();
        Collection<Customer> customers = customerRepository.findAll();
        for(Customer customer: customers) {
            result.add(CustomerAdapter.getCustomerDTOFromCustomer(customer));
        }
        return result;
    }

    @Override
    public CustomerDTO getCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return CustomerAdapter.getCustomerDTOFromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(long customerId, CustomerDTO customerDTO) throws Exception {
        Customer customer = null;
        try {
            customer = customerRepository.findById(customerId).get();

            customer.setName(customerDTO.getName());
            customer = customerRepository.save(customer);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return CustomerAdapter.getCustomerDTOFromCustomer(customer);
    }

    @Override
    public void deleteCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDTO addCustomer(Customer customer) {
        customer = customerRepository.save(customer);
        return CustomerAdapter.getCustomerDTOFromCustomer(customer);
    }
}
