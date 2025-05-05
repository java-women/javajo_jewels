package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String products(Model model) {
        System.out.println("called getProducts");

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(createProduct(i + 1));
        }

        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/{id}")
    public Product getProductDetail(@PathVariable(name = "id") String id){
        System.out.println("called getProductDetail");
        return createProduct(Integer.parseInt(id));
    }

    private Product createProduct(Integer id) {
        Product product= new Product();
        product.setId(id);
        product.setName("商品"+ id);
        product.setDescription("商品" + id + "の説明");
        product.setPrice(3400);
        product.setImageUrl("https://sunho.store/cdn/shop/files/968615.jpg?v=1745974078");
        return product;
    }

}
