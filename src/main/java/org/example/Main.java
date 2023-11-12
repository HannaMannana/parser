package org.example;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.method.JsonDeserialization.parseJsonArrayGen;
import static org.example.method.JsonSerialization.getJson;

public class Main {
    public static void main(String[] args) throws Exception {


        Product firstProduct = new Product();
        firstProduct.setId(UUID.fromString("f0445043-1fcc-4229-ba73-809c49e9e154"));
        firstProduct.setName("Water");
        firstProduct.setPrice(BigDecimal.valueOf(1.12));

        Product secondProduct = new Product();
        secondProduct.setId(UUID.fromString("e18628d0-2738-4f57-bfb1-9b2e7967d318"));
        secondProduct.setName("Butter");
        secondProduct.setPrice(BigDecimal.valueOf(3.36));


        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);


        Order order = new Order();
        order.setId(UUID.fromString("a641dbe9-7c3e-45e4-811f-8fd284b51a0e"));
        order.setProducts(products);

        Order blaOrder = new Order();
        blaOrder.setId(UUID.fromString("8e0ea384-cfd9-453f-8cc4-76bed2cdc444"));
        blaOrder.setProducts(products);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(blaOrder);

        Customer customer1 = new Customer();
        customer1.setId(UUID.fromString("8ed322dc-925a-4614-9755-29947857dd21"));
        customer1.setFirstname(null);
        customer1.setLastname("Potter");
        customer1.setOrders(orders);

        Customer customer2 = new Customer();
        customer2.setId(UUID.fromString("4b4eaa5e-0d04-4bb5-b8b9-77a175e703df"));
        customer2.setFirstname("Ron");
        customer2.setLastname("Weasley");
        customer2.setOrders(orders);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        System.out.println(getJson(customers));

        String jsonArray = "[{\"id\":\"e18628d0-2738-4f57-bfb1-9b2e7967d318\",\"name\": \"Butter\",\"price\":3.3},{\"id\":\"f0445043-1fcc-4229-ba73-809c49e9e154\",\"name\": \"Water\",\"price\":1.12}]";

        System.out.println(parseJsonArrayGen(jsonArray, Product.class));

    }
}