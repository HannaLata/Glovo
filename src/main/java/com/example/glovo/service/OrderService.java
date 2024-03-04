package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    private final Map<String, Order> orders = new HashMap<>();

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void updateOrder(String orderId, Order updatedOrder) {
        orders.put(orderId, updatedOrder);
    }

    public void deleteOrder(String orderId) {
        orders.remove(orderId);
    }

    public void addProductToOrder(String orderId, Product product) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.getProducts().add(product);
        }
    }

    public void deleteProductFromOrder(String orderId, String productId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.getProducts().removeIf(product -> product.getId().equals(productId));
        }
    }
}
