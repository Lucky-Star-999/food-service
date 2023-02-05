package com.example.foodservicev1.entity;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Order {
    private String id;
    private String restaurantUsername;
    private String customerEmail;
    private LocalDateTime createdDate;
}
