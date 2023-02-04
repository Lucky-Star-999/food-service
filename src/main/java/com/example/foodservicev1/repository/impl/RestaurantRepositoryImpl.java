package com.example.foodservicev1.repository.impl;

import com.example.foodservicev1.entity.Restaurant;
import com.example.foodservicev1.mapper.AdminRowMapper;
import com.example.foodservicev1.mapper.RestaurantRowMapper;
import com.example.foodservicev1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Restaurant> findAll() {
        return jdbcTemplate.query("SELECT * FROM RESTAURANT", new RestaurantRowMapper());
    }
}
