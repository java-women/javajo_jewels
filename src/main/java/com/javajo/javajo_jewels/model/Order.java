package com.javajo.javajo_jewels.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private String orderDate;
    private Integer totalAmount;
    private List<Product> items;
}
