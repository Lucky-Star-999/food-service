package com.example.foodservicev1.service;

import com.example.foodservicev1.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public List<Admin> findAll();
}
