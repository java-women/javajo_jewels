package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CartController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CartControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    CartService cartService;

    @Test
    void cartTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        var cart = new Cart();
        session.setAttribute("cart", cart);

        mockMvc.perform(get("/cart").session(session))
                .andDo(print())
                .andExpect(view().name("cart"));
    }

    @Test
    void addCartTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        var cart = new Cart();
        session.setAttribute("cart", cart);

        when(cartService.addCart(any(Cart.class), anyInt())).thenReturn(cart);

        mockMvc.perform(post("/cart")
                        .param("productId", "1")
                        .session(session))
                        .andDo(print())
                        .andExpect(view().name("cart"));
        verify(cartService, times(1)).addCart(any(Cart.class), anyInt());

    }

    @Test
    void deleteProductTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        var cart = new Cart();
        session.setAttribute("cart", cart);

        when(cartService.deleteCart(any(Cart.class), anyInt())).thenReturn(cart);

        mockMvc.perform(delete("/cart/products/1")
                        .param("productId", "1")
                        .session(session))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("cart"));

        verify(cartService, times(1)).deleteCart(any(Cart.class), anyInt());
    }
}
