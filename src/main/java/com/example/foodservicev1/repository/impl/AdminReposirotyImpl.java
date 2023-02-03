package com.example.foodservicev1.repository.impl;

import com.example.foodservicev1.entity.Admin;
import com.example.foodservicev1.mapper.AdminRowMapper;
import com.example.foodservicev1.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminReposirotyImpl implements AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Admin> findAll() {
        return jdbcTemplate.query("SELECT * FROM ADMIN", new AdminRowMapper());
    }
}
