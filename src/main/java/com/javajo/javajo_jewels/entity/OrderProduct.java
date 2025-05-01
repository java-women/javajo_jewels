package com.javajo.javajo_jewels.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderProduct {
    @Id
    Integer id;
    Integer orderId;
    Integer productId;
}
