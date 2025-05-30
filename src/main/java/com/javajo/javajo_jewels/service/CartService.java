package com.javajo.javajo_jewels.service;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final ProductRepository productRepository;

    public Cart addCart(Cart cart, Integer productId) {
        Product product = productRepository.findById(productId).map(Product::from).orElse(null);
        if (product == null) {
            return cart;
        }

        List<Product> products = new ArrayList<>();
        if (cart != null) {
            Optional<Product> cartProduct = cart.getProducts().stream().filter(p -> p.getId().equals(productId)).findFirst();
            if (cartProduct.isPresent()) {
                return cart;
            }
            products.addAll(cart.getProducts());
        }
        products.add(product);

        Integer totalAmount = products.stream().mapToInt(Product::getPrice).sum();

        return new Cart(totalAmount, products);
    }

    public Cart deleteCart(Cart cart, Integer productId) {
        List<Product> products = cart.getProducts().stream().filter(p -> !p.getId().equals(productId)).toList();
        Integer totalAmount = products.stream().mapToInt(Product::getPrice).sum();
        return new Cart(totalAmount, products);
    }
}
