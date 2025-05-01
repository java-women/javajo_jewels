package com.javajo.javajo_jewels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajo.javajo_jewels.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
