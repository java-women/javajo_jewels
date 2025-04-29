package com.javajo.javajo_jewels.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer productId;
    private Integer quantity;
    private Product product;
}


