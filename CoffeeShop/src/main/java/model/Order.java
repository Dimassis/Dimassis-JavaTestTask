package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<MenuItem> items = new ArrayList<>();
    private double discountPercent = 0.0;

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        return total - (total * discountPercent / 100);
    }

    public void applyDiscount(double percent) {
        this.discountPercent = percent;
    }
}
