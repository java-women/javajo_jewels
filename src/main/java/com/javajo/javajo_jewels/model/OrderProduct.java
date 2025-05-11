package com.javajo.javajo_jewels.model;

import com.javajo.javajo_jewels.entity.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    private Integer id;
    private Integer orderId;
    private Integer productId;

    public static OrderProduct from(OrderProductEntity entity) {
        return new OrderProduct(
                entity.getId(),
                entity.getOrderId(),
                entity.getProductId()
        );
    }
}
