package com.javajo.javajo_jewels.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.javajo.javajo_jewels.entity.ProductEntity;
import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest
@Import(ProductService.class)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
//        var product1 = ProductEntity.builder()
//                .name("きらめきリボンブレスレット")
//                .price(200)
//                .description("大きなリボンがついたブレスレット。特別な日にぴったりのアクセ。")
//                .imageUrl("/images/product_1.jpg")
//                .build();
//        var product2 = ProductEntity.builder()
//                .name("ふわもこユニコーンポーチ")
//                .price(550)
//                .description("ふわふわ手触りのユニコーン型ポーチ。小物をかわいく収納。")
//                .imageUrl("/images/product_2.png")
//                .build();
//        var product3 = ProductEntity.builder()
//                .name("スイートキャンディボールペン")
//                .price(180)
//                .description("カラフルなキャンディ風デザインのボールペン。友だちに自慢しちゃおう！")
//                .imageUrl("/images/product_3.png")
//                .build();
//        productRepository.saveAll(List.of(product1, product2, product3));
    }

    @Test
    void getAllProductsTest() {
        var actual = productService.getAllProducts();

        assertThat(actual)
                .hasSize(3)
                .extracting(
                        Product::getName,
                        Product::getDescription,
                        Product::getPrice,
                        Product::getImageUrl)
                .containsExactlyInAnyOrder(
                        tuple("きらめきリボンブレスレット", "大きなリボンがついたブレスレット。特別な日にぴったりのアクセ。", 200, "/images/product_1.png"),
                        tuple("ふわもこユニコーンポーチ", "ふわふわ手触りのユニコーン型ポーチ。小物をかわいく収納。", 550, "/images/product_2.png"),
                        tuple("スイートキャンディボールペン", "カラフルなキャンディ風デザインのボールペン。友だちに自慢しちゃおう！", 180, "/images/product_3.png"));
    }

    @Test
    void getProductByIdTest() {
        var actual = productService.getProductById(1);

        assertThat(actual)
                .extracting(
                        Product::getName,
                        Product::getDescription,
                        Product::getPrice,
                        Product::getImageUrl)
                .containsExactly(
                        "きらめきリボンブレスレット",
                        "大きなリボンがついたブレスレット。特別な日にぴったりのアクセ。",
                        200,
                        "/images/product_1.png");
    }
}
