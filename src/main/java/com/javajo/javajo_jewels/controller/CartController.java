package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
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

    @PostMapping
    public String addCart(@RequestParam("product-id") int productId) {
        System.out.println("called addCart");
        System.out.println(productId);
        return "cart";
    }

    @DeleteMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "productId") String productId) {
        System.out.println("called deleteProduct");
    }

    private Cart createCart(Integer id, Integer amount) {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setTotalAmount(amount);

        List<Product> products = new ArrayList<>();

        for (int i = 0 ; i < 3; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName("商品" + i);
            product.setPrice(100 * i);
            product.setImageUrl("https://test.com/" + i + ".png");
            products.add(product);
        }

        cart.setProducts(products);

        return cart;
    }
}
