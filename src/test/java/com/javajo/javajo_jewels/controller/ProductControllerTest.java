package com.javajo.javajo_jewels.controller;


import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = ProductController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    ProductService productService;


    @Test
    void getProductsTest() throws Exception {
        var result = new ArrayList<Product>();
        result.add(createProduct(1));
        doReturn(result).when(productService).getAllProducts();
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andDo(print())
                .andExpect(content().string(containsString("http://test.com/1.png")))
                .andExpect(content().string(containsString("商品1")))
                .andExpect(content().string(containsString("100")))
                .andExpect(view().name("products"));

    }

    @Test
    void getProductDetailTest() throws Exception {
        var result = createProduct(2);
        doReturn(result).when(productService).getProductById(any());
        mockMvc.perform(MockMvcRequestBuilders.get("/products/2"))
                .andDo(print())
                .andExpect(content().string(containsString("http://test.com/2.png")))
                .andExpect(content().string(containsString("商品2")))
                .andExpect(content().string(containsString("200")))
                .andExpect(view().name("product-detail"));
    }

    private Product createProduct(int id) {
        var result = new Product();
        result.setId(id);
        result.setName("商品" + id);
        result.setPrice(id * 100);
        result.setDescription("商品" + id + "の説明");
        result.setImageUrl("http://test.com/" + id + ".png");
        return result;
    }

}
