package com.javajo.javajo_jewels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajo.javajo_jewels.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
