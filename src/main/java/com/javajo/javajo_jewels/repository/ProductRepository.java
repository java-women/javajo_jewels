package com.javajo.javajo_jewels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajo.javajo_jewels.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
