package com.example.foodservicev1.repository.impl;

import com.example.foodservicev1.entity.Food;
import com.example.foodservicev1.mapper.FoodRowMapper;
import com.example.foodservicev1.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodRepositoryImpl implements FoodRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Food> findByRestaurantUsername(String restaurantUsername) {
        try {
            return jdbcTemplate.query("SELECT * FROM FOOD WHERE RestaurantUsername = ?",
                    new FoodRowMapper(), restaurantUsername);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Food findById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM FOOD WHERE Id = ?", new FoodRowMapper(), id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int save(Food food) {
        return 0;
    }

    @Override
    public int update(Food food) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int deleteByRestaurantUsername(String restaurantUsername) {
        return 0;
    }
}
