package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping
    public String getCart(Model model) {
        System.out.println("called getCart");
        Cart cart = createCart(1);

        model.addAttribute("cart", cart);

        return "cart";
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

    private Cart createCart(Integer id) {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setTotalAmount(300);

        List<Product> products = new ArrayList<>();

        for (int i = 1 ; i < 4; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName("商品" + i);
            product.setPrice(100 * i);
            product.setImageUrl("https://sunho.store/cdn/shop/files/968615.jpg?v=1745974078");
            products.add(product);
        }

        cart.setProducts(products);

        return cart;
    }
}
