package com.example.glovo.controller;

import com.example.glovo.model.Order;
import com.example.glovo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order createdOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order updatedOrder) {
        Order existingOrder = orderService.getOrder(orderId);
        if (existingOrder != null) {
            updatedOrder.setId(orderId);
            Order savedOrder = orderService.updateOrder(updatedOrder);
            return ResponseEntity.ok(savedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        if (orderService.getOrder(orderId) != null) {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/orders/{orderId}/products/{productId}")
    public ResponseEntity<Order> addProductToOrder(@PathVariable String orderId, @PathVariable String productId) {
        Order updatedOrder = orderService.addProductToOrder(orderId, productId);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/orders/{orderId}/products/{productId}")
    public ResponseEntity<Order> deleteProductFromOrder(@PathVariable String orderId, @PathVariable String productId) {
        Order updatedOrder = orderService.deleteProductFromOrder(orderId, productId);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
