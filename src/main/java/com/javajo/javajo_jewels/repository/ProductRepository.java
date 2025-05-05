package com.javajo.javajo_jewels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajo.javajo_jewels.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
