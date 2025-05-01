package com.javajo.javajo_jewels.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Order(
        @Id
        Integer id,
        Integer amount
) {}
