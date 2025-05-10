package com.javajo.javajo_jewels.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajo.javajo_jewels.entity.OrderEntity;
import com.javajo.javajo_jewels.entity.OrderProductEntity;
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
    public Integer createOrder(List<Integer> productIds) {
        var amount = productIds.stream()
                .map(id -> productService.getProductById(id).getPrice())
                .reduce(0, Integer::sum);

        var order = orderRepository.save(OrderEntity.builder().amount(amount).build());
        var orderProducts = productIds.stream()
                .map(id -> OrderProductEntity.builder()
                        .orderId(order.getId())
                        .productId(id)
                        .build())
                .toList();
        orderProductRepository.saveAll(orderProducts);

        return order.getId();
    }
}
