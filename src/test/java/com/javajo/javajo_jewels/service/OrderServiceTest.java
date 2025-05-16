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
    }

    @Test
    void createOrderTest() {
        var productIds = List.of(1, 2);
        orderService.createOrder(productIds);

        var orders = orderRepository.findAll();
        assertThat(orders)
                .hasSize(1)
                .extracting(OrderEntity::getAmount)
                .contains(335);

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
