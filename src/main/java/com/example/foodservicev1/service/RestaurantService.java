package com.example.foodservicev1.service;

import com.example.foodservicev1.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    public List<Restaurant> findAll();
}
