package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

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
        Cart cart = cartService.addCart(sessionCart, productId);
        session.setAttribute("cart", cart);

        model.addAttribute("cart", cart);

        return "cart";
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam(name = "productId") Integer productId, HttpSession session, Model model) {
        logger.debug("called deleteProduct");

        Cart sessionCart = (Cart) session.getAttribute("cart");
        Cart cart = cartService.deleteCart(sessionCart, productId);
        session.setAttribute("cart", cart);

        model.addAttribute("cart", cart);

        return "redirect:cart";
    }
}
