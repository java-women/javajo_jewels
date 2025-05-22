package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(Model model) {
        logger.debug("called products");

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable(name = "id") Integer id, Model model) {
        logger.debug("called productDetail");

        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "product-detail";
    }
}
