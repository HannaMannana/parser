package org.example.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {
    public UUID id;
    public String name;
    public BigDecimal price;

    public Product(){

    }

    public Product(UUID id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
