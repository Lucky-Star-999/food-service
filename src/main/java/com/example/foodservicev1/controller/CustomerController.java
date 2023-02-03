package com.example.foodservicev1.controller;

import com.example.foodservicev1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class CustomerController {
    @Autowired
    private CustomerService customerService;


}
