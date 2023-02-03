package com.example.foodservicev1.mapper;

import com.example.foodservicev1.entity.Customer;
import com.example.foodservicev1.entity.Restaurant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantRowMapper implements RowMapper<Restaurant> {

    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(rs.getString("Id"));
        restaurant.setEmail(rs.getString("Email"));
        restaurant.setPassword(rs.getString("Password"));
        restaurant.setName(rs.getString("Name"));
        restaurant.setImageLink(rs.getString("Phone"));
        restaurant.setAddress(rs.getString("Address"));
        return restaurant;
    }
}
