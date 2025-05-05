package com.javajo.javajo_jewels.service;

import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.javajo.javajo_jewels.entity.OrderEntity;
import com.javajo.javajo_jewels.entity.OrderProductEntity;
import com.javajo.javajo_jewels.entity.ProductEntity;
import com.javajo.javajo_jewels.repository.OrderProductRepository;
import com.javajo.javajo_jewels.repository.OrderRepository;
import com.javajo.javajo_jewels.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({ProductService.class, OrderService.class})
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @BeforeEach
    void setUp() {
        var product1 = ProductEntity.builder()
                .name("きらめきリボンブレスレット")
                .price(200)
                .description("大きなリボンがついたブレスレット。特別な日にぴったりのアクセ。")
                .imageUrl("http://image1.jpg")
                .build();
        var product2 = ProductEntity.builder()
                .name("ふわもこユニコーンポーチ")
                .price(550)
                .description("ふわふわ手触りのユニコーン型ポーチ。小物をかわいく収納。")
                .imageUrl("http://image2.jpg")
                .build();
        var product3 = ProductEntity.builder()
                .name("スイートキャンディボールペン")
                .price(180)
                .description("カラフルなキャンディ風デザインのボールペン。友だちに自慢しちゃおう！")
                .imageUrl("http://image3.jpg")
                .build();
        productRepository.saveAll(List.of(product1, product2, product3));
    }

    @Test
    void createOrderTest() {
        var productIds = List.of(1, 2);
        orderService.createOrder(productIds);

        var orders = orderRepository.findAll();
        assertThat(orders)
                .hasSize(1)
                .extracting(OrderEntity::getAmount)
                .contains(750);

        var orderId = orders.getFirst().getId();
        var orderProducts = orderProductRepository.findAll();
        assertThat(orderProducts)
                .hasSize(2)
                .extracting(
                        OrderProductEntity::getOrderId,
                        OrderProductEntity::getProductId)
                .containsExactlyInAnyOrder(
                        Tuple.tuple(orderId, 1),
                        Tuple.tuple(orderId, 2)
                );
    }
}
