package com.javajo.javajo_jewels.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Order;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.service.OrderService;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = OrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    OrderService orderService;
    @MockitoBean
    Cart sessionCart;

    @Test
    void createOrderTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("cart", sessionCart);

        List<Integer> productIds = List.of(1, 2, 3);
        when(sessionCart.getProducts()).thenReturn(productIds.stream()
                .map(id -> new Product(id, "name", "desctiption", 100, "test"))
                .toList());

        var order = new Order(1, 600, new ArrayList<>());
        doReturn(order).when(orderService).createOrder(productIds);


        mockMvc.perform(post("/orders").session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("complete"));

        verify(orderService, times(1)).createOrder(productIds);
    }
}
