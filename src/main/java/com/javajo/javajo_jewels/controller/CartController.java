package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartController {
    @GetMapping
    public List<Cart> getCart() {
        System.out.println("called getCart");
        List<Cart> response = new ArrayList<>();
        response.add(createCart(1, 1));
        response.add(createCart(2, 1));
        response.add(createCart(3, 1));
        return response;
    }

    @PostMapping("/items")
    public List<Cart> addCart(@RequestBody Cart request) {
        System.out.println("called addCart");
        List<Cart> response = new ArrayList<>();
        response.add(request);
        response.add(createCart(5, 1));
        response.add(createCart(4, 1));
        response.add(createCart(3, 1));
        return response;
    }

    @PutMapping("/items/{productId}")
    public List<Cart> updateCart(@PathVariable(name = "productId") String productId, @RequestBody Cart request){
        System.out.println("called updateCart");
        List<Cart> response = new ArrayList<>();
        response.add(request);
        response.add(createCart(100, 1));
        response.add(createCart(101, 1));
        response.add(createCart(102, 1));
        return response;
    }

    @DeleteMapping("/items/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "productId") String productId) {
        System.out.println("called deleteProduct");
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart() {
        System.out.println("called deleteCart");
    }

    private Cart createCart(Integer productId, Integer quantity) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        Product product = new Product();
        product.setId(productId);
        product.setName("商品" + productId);
        return cart;
    }
}
