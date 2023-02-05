package com.example.foodservicev1.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderFood {
    private String orderId;
    private String foodId;
    private int quantity;
}
