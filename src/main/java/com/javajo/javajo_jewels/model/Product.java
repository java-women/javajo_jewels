package com.javajo.javajo_jewels.model;

import com.javajo.javajo_jewels.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String imageUrl;

    public static Product from(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice().intValue(),
                entity.getImageUrl()
        );
    }
}
