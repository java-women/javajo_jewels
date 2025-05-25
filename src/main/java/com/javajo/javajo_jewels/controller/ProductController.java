package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String products(Model model) {
        logger.debug("called products");

        List<Product> products = List.of(
                new Product(
                        1,
                        "商品1",
                        "商品説明1",
                        100,
                        "/images/product-1.png"
                ),
                new Product(
                        2,
                        "商品2",
                        "商品説明2",
                        200,
                        "/images/product-2.png"
                )
        );
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable(name = "id") Integer id, Model model) {
        logger.debug("called productDetail");

        Product product = new Product(
                id,
                "商品%d".formatted(id),
                "商品説明%d".formatted(id),
                id * 100,
                "/images/product-%d.png".formatted(id)
        );
        model.addAttribute("product", product);

        return "product-detail";
    }
}
