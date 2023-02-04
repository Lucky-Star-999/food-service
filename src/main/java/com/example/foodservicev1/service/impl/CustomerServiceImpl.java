package com.example.foodservicev1.service.impl;

import com.example.foodservicev1.entity.Customer;
import com.example.foodservicev1.repository.CustomerRepository;
import com.example.foodservicev1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public int save(Customer customer) {
        return customerRepository.save(customer);
    }
}
