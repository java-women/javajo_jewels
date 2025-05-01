package com.javajo.javajo_jewels.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record CartItem(
        @Id
        Integer id,
        Integer cartId,
        Integer productId
) {}
