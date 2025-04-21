package service;

import model.Order;
import model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderProcessor {

    public static List<Product> filterAndDiscount(List<Order> orders, StringBuilder log) {
        LocalDate start = LocalDate.of(2021, 2, 1);
        LocalDate end = LocalDate.of(2021, 4, 1);

        return orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> !order.getOrderDate().isBefore(start) && !order.getOrderDate().isAfter(end))
                .peek(order -> log.append(">> Xử lý: ").append(order).append("\n"))
                .flatMap(order -> order.getProducts().stream())
                .map(product -> {
                    if ("Toys".equalsIgnoreCase(product.getCategory())) {
                        return new Product(product.getName(), product.getCategory(), round(product.getPrice() * 0.9));
                    }
                    return product;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
