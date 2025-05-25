package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String cart(HttpSession session, Model model) {
        logger.debug("called cart");

        Cart sessionCart = (Cart) session.getAttribute("cart");

        if (sessionCart == null) {
            sessionCart = new Cart(0, new ArrayList<>());
            session.setAttribute("cart", sessionCart);
        }

        model.addAttribute("cart", sessionCart);

        return "cart";
    }

    @PostMapping
    public String addCart(@RequestParam("productId") Integer productId, HttpSession session, Model model) {
        logger.debug("called addCart");

        Cart sessionCart = (Cart) session.getAttribute("cart");
        Cart cart = addCart(sessionCart, productId);
        session.setAttribute("cart", cart);

        model.addAttribute("cart", cart);

        return "cart";
    }

    // Serviceの処理を実装したら入れ替えるので仮の処理です
    private Cart addCart(Cart sessionCart, Integer productId) {
        if (sessionCart == null) {
            sessionCart = new Cart(0, new ArrayList<>());
        }

        List<Product> newProducts = new ArrayList<>(sessionCart.getProducts());
        newProducts.add(
                new Product(
                        productId,
                        "商品%d".formatted(productId),
                        "商品説明%d".formatted(productId),
                        productId * 100,
                        "/images/product-%d.png".formatted(productId)
                )
        );

        return new Cart(
                sessionCart.getTotalAmount() + (productId * 100),
                newProducts
        );
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam(name = "productId") Integer productId, HttpSession session, Model model) {
        logger.debug("called deleteProduct");

        Cart sessionCart = (Cart) session.getAttribute("cart");
        Cart cart = deleteProduct(sessionCart, productId);
        session.setAttribute("cart", cart);

        model.addAttribute("cart", cart);

        return "redirect:cart";
    }

    // Serviceの処理を実装したら入れ替えるので仮の処理です
    private Cart deleteProduct(Cart sessionCart, Integer productId) {
        List<Product> products = sessionCart.getProducts().stream().filter(p -> !p.getId().equals(productId)).toList();
        Integer totalAmount = products.stream().mapToInt(Product::getPrice).sum();
        return new Cart(totalAmount, products);
    }
}
