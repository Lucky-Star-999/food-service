package com.example.foodservicev1.repository;

import com.example.foodservicev1.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository {
    public List<Restaurant> findAll();
}
