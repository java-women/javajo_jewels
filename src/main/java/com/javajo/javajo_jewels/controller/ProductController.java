package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @GetMapping
    public List<Product> getProducts() {
        System.out.println("called getProducts");
        List<Product> response = new ArrayList<>();
        response.add(createProduct(1));
        response.add(createProduct(2));
        response.add(createProduct(3));
        return response;
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
        return product;
    }

}
