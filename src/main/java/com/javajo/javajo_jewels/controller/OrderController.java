package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Order;
import com.javajo.javajo_jewels.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @PostMapping
    public Order createOrder(@RequestBody Cart request) {
        System.out.println("called createOrder");
        double orderId = Math.random();
        Order response = createOrder(Double.valueOf(orderId).intValue());

        List<Product> products = new ArrayList<>();

        for (int i = 0 ; i < 3; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName("商品" + i);
            product.setPrice(100 * i);
            product.setImageUrl("https://test.com/" + i + ".png");
            products.add(product);
        }

        response.setItems(products);
        return response;
    }

    public Order createOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderDate("9999-12-31");
        order.setTotalAmount(orderId * 100);
        return order;
    }
}
