package com.example.foodservicev1.repository.impl;

import com.example.foodservicev1.entity.Admin;
import com.example.foodservicev1.entity.Customer;
import com.example.foodservicev1.mapper.AdminRowMapper;
import com.example.foodservicev1.mapper.CustomerRowMapper;
import com.example.foodservicev1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM CUSTOMER", new CustomerRowMapper());
    }

    @Override
    public int save(Customer customer) {
        try {
            return jdbcTemplate.update("INSERT INTO CUSTOMER ( Email, Password, Fullname, Phone )" +
                            "VALUES ( ?, ?, ?, ? )",
                    new Object[]{customer.getEmail(), customer.getPassword(), customer.getFullname(), customer.getPhone()});
        } catch (Exception e) {
            return 0;
        }
    }
}
