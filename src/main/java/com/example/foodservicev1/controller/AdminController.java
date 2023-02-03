package com.example.foodservicev1.controller;

import com.example.foodservicev1.service.AdminService;
import com.example.foodservicev1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/admin")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/api/admin/admins")
    public String findAll(Model model) {
        model.addAttribute("admins", adminService.findAll());
        return "admin/find-admin";
    }

    @GetMapping("/api/admin/customers")
    public String findAllCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "admin/find-customer";
    }
}
