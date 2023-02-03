package com.example.foodservicev1.repository;

import com.example.foodservicev1.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository {
    public List<Admin> findAll();
}
