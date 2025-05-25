package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Order;
import com.javajo.javajo_jewels.model.Product;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    public String createOrder(HttpSession session, Model model) {
        logger.debug("called createOrder");

        Cart sessionCart = (Cart) session.getAttribute("cart");

        String orderDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        Integer totalAmount = sessionCart.getProducts().stream().mapToInt(Product::getPrice).sum();
        Order order = new Order(
                1,
                orderDate,
                totalAmount,
                List.of()
        );

        model.addAttribute("order", order);

        Cart cart = new Cart(0, new ArrayList<>());
        session.setAttribute("cart", cart);

        return "complete";
    }
}
