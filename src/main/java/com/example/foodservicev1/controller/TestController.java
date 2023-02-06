package com.example.foodservicev1.controller;

import com.example.foodservicev1.entity.ServiceOrder;
import com.example.foodservicev1.service.impl.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping("/api/test/order")
    public int saveOrder(@RequestBody ServiceOrder serviceOrder) {
        return serviceOrderService.save(serviceOrder);
    }

    @GetMapping("/api/test/order/{restaurantUsername}")
    public List<ServiceOrder> saveOrder(@PathVariable String restaurantUsername) {
        System.out.println(serviceOrderService.findByRestaurantUsername(restaurantUsername).get(0));
        return serviceOrderService.findByRestaurantUsername(restaurantUsername);
    }
}
