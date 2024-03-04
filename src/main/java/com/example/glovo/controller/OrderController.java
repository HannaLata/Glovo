package com.example.glovo.controller;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
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
    public ResponseEntity<Void> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable String orderId, @RequestBody Order updatedOrder) {
        if (orderService.getOrder(orderId) != null) {
            orderService.updateOrder(orderId, updatedOrder);
            return ResponseEntity.ok().build();
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

    @PatchMapping("/orders/{orderId}/products")
    public ResponseEntity<Void> addProductToOrder(@PathVariable String orderId, @RequestBody Product product) {
        orderService.addProductToOrder(orderId, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/orders/{orderId}/products/{productId}")
    public ResponseEntity<Void> deleteProductFromOrder(@PathVariable String orderId, @PathVariable String productId) {
        orderService.deleteProductFromOrder(orderId, productId);
        return ResponseEntity.ok().build();
    }
}
