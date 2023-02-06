package com.example.foodservicev1.controller;

import com.example.foodservicev1.entity.ServiceOrder;
import com.example.foodservicev1.service.impl.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping("/api/test/order")
    public int saveOrder(@RequestBody ServiceOrder serviceOrder) {
        return serviceOrderService.save(serviceOrder);
    }
}
