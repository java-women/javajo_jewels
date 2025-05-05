package com.javajo.javajo_jewels.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(Product::from)
                .toList();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .map(Product::from)
                .orElse(null);
    }
}
