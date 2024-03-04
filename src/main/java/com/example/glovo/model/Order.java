package com.example.glovo.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String id;
    private List<Product> products;
}
