package com.javajo.javajo_jewels.service;

import com.javajo.javajo_jewels.entity.ProductEntity;
import com.javajo.javajo_jewels.model.Cart;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CartService.class)
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    ProductRepository productRepository;

    List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();

        var product1 = ProductEntity.builder()
                .name("きらめきリボンブレスレット")
                .price(new BigDecimal("200.0"))
                .description("大きなリボンがついたブレスレット。特別な日にぴったりのアクセ。")
                .imageUrl("http://image1.jpg")
                .build();
        var product2 = ProductEntity.builder()
                .name("ふわもこユニコーンポーチ")
                .price(new BigDecimal("550.0"))
                .description("ふわふわ手触りのユニコーン型ポーチ。小物をかわいく収納。")
                .imageUrl("http://image2.jpg")
                .build();
        var product3 = ProductEntity.builder()
                .name("スイートキャンディボールペン")
                .price(new BigDecimal("180.0"))
                .description("カラフルなキャンディ風デザインのボールペン。友だちに自慢しちゃおう！")
                .imageUrl("http://image3.jpg")
                .build();
        productRepository.saveAll(List.of(product1, product2, product3));
        products = productRepository.findAll().stream().map(Product::from).toList();
    }

    @Test
    void addCartCreateTest() {
        var actual = cartService.addCart(null, products.get(0).getId());

        assertThat(actual)
                .isEqualTo(new Cart(200, List.of(products.get(0))));
    }

    @Test
    void addCartUpdateTest() {
        var actual = cartService.addCart(new Cart(750, List.of(
                products.get(0),
                products.get(1))), 3);

        assertThat(actual)
                .isEqualTo(
                        new Cart(930, List.of(
                                products.get(0),
                                products.get(1),
                                products.get(2))));
    }
}