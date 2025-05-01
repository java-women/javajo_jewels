package com.javajo.javajo_jewels.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Cart(
        @Id
        Integer id
) {}
