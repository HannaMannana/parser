package org.example.entity;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Customer {
    public UUID id;
    public String firstname;
    public String lastname;
    public List<Order> orders;

    public Customer(){

    }

    public Customer(UUID id, String firstname, String lastname, Order orders){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.orders = (List<Order>) orders;
    }

}
