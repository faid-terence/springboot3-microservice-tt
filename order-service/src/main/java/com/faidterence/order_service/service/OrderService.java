package com.faidterence.order_service.service;


import com.faidterence.order_service.Client.InventoryClient;
import com.faidterence.order_service.dto.OrderRequest;
import com.faidterence.order_service.model.Order;
import com.faidterence.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private  final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var productInStock = inventoryClient.inStock(orderRequest.skuCode(), orderRequest.quantity());
        if (!productInStock) {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        // Save the order to the database
        orderRepository.save(order);

    }
}
