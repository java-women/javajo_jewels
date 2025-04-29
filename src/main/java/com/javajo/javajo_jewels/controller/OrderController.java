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
        List<Cart> items = new ArrayList<>();
        items.add(request);
        response.setItems(items);
        return response;
    }

    // TODO SessionIdの受け渡し
    @GetMapping
    public List<Order> getOrders() {
        System.out.println("called getOrders");
        List<Order> response = new ArrayList<>();
        response.add(createOrder(1));
        response.add(createOrder(2));
        response.add(createOrder(3));
        return response;
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable (name = "orderId") String orderId) {
        System.out.println("called getOrder");
        Order response = createOrder(1);
        Product product = new Product();
        product.setId(1);
        product.setName("商品1");
        Cart cart = new Cart();
        cart.setProductId(1);
        cart.setQuantity(1);
        cart.setProduct(product);
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        response.setItems(carts);
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
