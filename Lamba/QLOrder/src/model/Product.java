package model;

import java.util.Objects;

public class Product {
    private final String name;
    private final String category;
    private final double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (%s): %.2f", name, category, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product p)) return false;
        return Double.compare(p.price, price) == 0 &&
                name.equals(p.name) &&
                category.equals(p.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }
}
