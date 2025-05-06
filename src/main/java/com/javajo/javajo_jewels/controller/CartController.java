package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public String cart(HttpSession session, Model model) {
        System.out.println("called getCart");

        Cart sessionCart = (Cart) session.getAttribute("cart");

        model.addAttribute("cart", sessionCart);

        return "cart";
    }

    @PostMapping
    public String addCart(@RequestParam("productId") int productId, HttpSession session, Model model) {
        System.out.println("called addCart");

        Cart sessionCart = (Cart) session.getAttribute("cart");
        Cart cart = cartService.addCart(sessionCart, productId);
        session.setAttribute("cart", cart);

        model.addAttribute("cart", cart);

        return "cart";
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@RequestParam(name = "productId") int productId, HttpSession session, Model model) {
        System.out.println("called deleteProduct");

        Cart sessionCart = (Cart) session.getAttribute("cart");
        Cart cart = cartService.deleteCart(sessionCart, productId);
        session.setAttribute("cart", cart);

        model.addAttribute("cart", cart);

        return "redirect:cart";
    }
}
