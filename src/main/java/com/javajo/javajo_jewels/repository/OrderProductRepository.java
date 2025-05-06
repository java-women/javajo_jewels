package com.javajo.javajo_jewels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajo.javajo_jewels.entity.OrderProductEntity;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer> {
}
