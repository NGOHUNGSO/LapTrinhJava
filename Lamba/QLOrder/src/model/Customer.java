package model;

public class Customer {
    private final int tier;

    public Customer(int tier) {
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Tier " + tier;
    }
}
