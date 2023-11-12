package org.example.entity;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Order {
    public UUID id;
    public List<Product> products;


    public Order(){

    }

    public Order(UUID id, Product products){
        this.id = id;
        this.products = (List<Product>) products;
    }
}
