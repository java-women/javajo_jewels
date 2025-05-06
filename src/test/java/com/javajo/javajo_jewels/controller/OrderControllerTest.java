package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = OrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    OrderService orderService;

    @Test
    void createOrderTest() throws Exception {
        var param = new Cart();
        var products = new ArrayList<Product>();
        products.add(createProduct(1));
        products.add(createProduct(2));
        param.setProducts(products);
        param.setTotalAmount(300);

        var productIds = Arrays.asList(1,2);
        doNothing().when(orderService).createOrder(productIds);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders"))
                .andDo(print());
                // TODO 返却結果の確認
                // .andExpect(view().name("products"));

        // verify(orderService, times(1)).createOrder(productIds);

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
