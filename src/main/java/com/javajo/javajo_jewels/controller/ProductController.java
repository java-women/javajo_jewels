package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Product;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public Product createProduct(@RequestBody Product request) {
        System.out.println("called createProduct");
        return createProduct(request.getId());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(name = "id") String id , @RequestBody Product request) {
        System.out.println("called createProduct");
        Product response = createProduct(Integer.parseInt(id));
        response.setId(request.getId());
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setPrice(request.getPrice());
        response.setImageUrl(request.getImageUrl());
        return response;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "id") String id) {
        System.out.println("called deleteProduct");
    }


    @GetMapping("/search")
    public List<Product> searchProduct(Product request) {
        System.out.println("called deleteProduct");
        System.out.println(request.getId());
        List<Product> response = new ArrayList<>();
        response.add(request);
        response.add(createProduct(4));
        response.add(createProduct(5));
        response.add(createProduct(6));
        return response;
    }


    private Product createProduct(Integer id) {
        Product product= new Product();
        product.setId(id);
        product.setName("商品"+ id);
        product.setDescription("商品" + id + "の説明");
        return product;
    }

}
