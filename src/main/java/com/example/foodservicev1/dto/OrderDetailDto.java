package com.example.foodservicev1.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderDetailDto {
    private String restaurantName;
    private String customerEmail;
    private String foodName;
    private double price;
    private int quantity;
}
