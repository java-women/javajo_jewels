package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)  {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(HttpSession session, Model model) {
        logger.debug("called createOrder");

        Cart sessionCart = (Cart) session.getAttribute("cart");

        var products = sessionCart.getProducts();
        var productIds = products.stream()
                .map(Product::getId)
                .toList();

        Integer orderId = orderService.createOrder(productIds);
        model.addAttribute("orderId", orderId);

        Cart cart = new Cart();
        cart.setTotalAmount(0);
        cart.setProducts(new ArrayList<Product>());
        session.setAttribute("cart", cart);

        return "order";
    }
}
