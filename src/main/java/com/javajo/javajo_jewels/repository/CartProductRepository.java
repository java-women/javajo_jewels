package com.javajo.javajo_jewels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajo.javajo_jewels.entity.CartProductEntity;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Integer> {
}
