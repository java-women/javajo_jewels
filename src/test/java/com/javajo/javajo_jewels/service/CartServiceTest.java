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
                .name("きらきらお星様チャーム")
                .price(125)
                .description("きらきら輝くお星様のチャーム☆ カバンやポーチにつければ、毎日がちょっぴり特別に♪")
                .imageUrl("/images/product-1.png")
                .build();
        var product2 = ProductEntity.builder()
                .name("虹のゆめかわヘアクリップ")
                .price(210)
                .description("パステルカラーの虹がきらっと光る、ゆめかわいいヘアクリップ。つけるだけで毎日がときめく魔法タイムに♪")
                .imageUrl("/images/product-2.png")
                .build();
        var product3 = ProductEntity.builder()
                .name("ときめきハートリング")
                .price(160)
                .description("つけるたび、ドキドキしちゃう♡ 小さなハートがきらっと光る、乙女心くすぐる指輪。今日はどの指につける？")
                .imageUrl("/images/product-3.png")
                .build();
        productRepository.saveAll(List.of(product1, product2, product3));
        products = productRepository.findAll().stream().map(Product::from).toList();
    }

    @Test
    void addCartCreateTest() {
        var actual = cartService.addCart(null, products.getFirst().getId());

        assertThat(actual)
                .isEqualTo(new Cart(125, List.of(products.getFirst())));
    }

    @Test
    void addCartUpdateTest() {
        var actual = cartService.addCart(new Cart(750, List.of(
                products.get(0),
                products.get(1))), products.get(2).getId());

        assertThat(actual)
                .isEqualTo(
                        new Cart(495, List.of(
                                products.get(0),
                                products.get(1),
                                products.get(2))));
    }

    @Test
    void deleteCartTest() {
        var actual = cartService.deleteCart(new Cart(750, List.of(
                products.get(0),
                products.get(1))), products.get(0).getId());

        assertThat(actual)
                .isEqualTo(new Cart(210, List.of(products.get(1))));
    }
}