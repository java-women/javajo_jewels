package com.javajo.javajo_jewels.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajo.javajo_jewels.entity.OrderEntity;
import com.javajo.javajo_jewels.entity.OrderProductEntity;
import com.javajo.javajo_jewels.model.Order;
import com.javajo.javajo_jewels.model.OrderProduct;
import com.javajo.javajo_jewels.repository.OrderProductRepository;
import com.javajo.javajo_jewels.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    @Transactional
    public Order createOrder(List<Integer> productIds) {
        Integer amount = productIds.stream()
                .mapToInt(id -> productService.getProductById(id).getPrice())
                .sum();

        OrderEntity order = orderRepository.save(OrderEntity.builder().amount(amount).build());

        Integer orderId = order.getId();
        List<OrderProductEntity> orderProducts = productIds.stream()
                .map(id -> OrderProductEntity.builder()
                        .orderId(orderId)
                        .productId(id)
                        .build())
                .toList();
        orderProductRepository.saveAll(orderProducts);

        String orderDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        List<OrderProduct> resultOrderProducts = orderProducts.stream().map(OrderProduct::from).toList();

        return new Order(order.getId(), orderDate, order.getAmount(), resultOrderProducts);
    }
}
