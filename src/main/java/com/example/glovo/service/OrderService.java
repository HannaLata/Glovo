package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrder(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order addOrder(Order order) {
        if (order.getId() == null) {
            throw new IllegalArgumentException("Order ID cannot be null.");
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(updatedOrder.getId());
        if (existingOrderOptional.isPresent()) {
            return orderRepository.save(updatedOrder);
        } else {
            throw new IllegalArgumentException("Order with ID " + updatedOrder.getId() + " does not exist.");
        }
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order addProductToOrder(String orderId, String productId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalOrder.isPresent() && optionalProduct.isPresent()) {
            Order order = optionalOrder.get();
            Product product = optionalProduct.get();
            order.getProducts().add(product);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    public Order deleteProductFromOrder(String orderId, String productId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalOrder.isPresent() && optionalProduct.isPresent()) {
            Order order = optionalOrder.get();
            Product product = optionalProduct.get();
            order.getProducts().remove(product);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }
}
