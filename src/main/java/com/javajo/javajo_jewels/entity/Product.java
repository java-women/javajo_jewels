package com.javajo.javajo_jewels.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    Integer id;
    String name;
    BigDecimal price;
    String description;
    String imageUrl;
}
