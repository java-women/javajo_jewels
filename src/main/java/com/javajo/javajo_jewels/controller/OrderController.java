package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Order;
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
import java.util.List;

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

        List<Product> products = sessionCart.getProducts();
        List<Integer> productIds = products.stream()
                .map(Product::getId)
                .toList();

        Order order = orderService.createOrder(productIds);
        model.addAttribute("order", order);

        Cart cart = new Cart(0, new ArrayList<>());
        session.setAttribute("cart", cart);

        return "order";
    }
}
