package model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final String status;
    private final Customer customer;
    private final List<Product> products;

    public Order(LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer, List<Product> products) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
        this.products = products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return String.format("Order(%s, Tier %d, %d sản phẩm)", orderDate, customer.getTier(), products.size());
    }
}
