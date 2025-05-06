package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Order;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)  {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@RequestBody Cart request, Model model) {
        logger.debug("called createOrder");

        var products = request.getProducts();
        var productIds = products.stream()
                .map(Product::getId)
                .toList();
        orderService.createOrder(productIds);

        return "products";
    }
}
