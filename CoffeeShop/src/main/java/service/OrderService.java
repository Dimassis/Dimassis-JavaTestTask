package service;

import model.MenuItem;
import model.Order;

import java.util.List;

public class OrderService {
    private final Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public void addItem(MenuItem item) {
        order.addItem(item);
    }

    public void applyPromoCode(String code) {
        switch (code.toUpperCase()) {
            case "WELCOME10" -> order.applyDiscount(10);
            case "VIP50" -> order.applyDiscount(50);
            default -> System.out.println("Недействительный промокод");
        }
    }

    public List<MenuItem> getItems() {
        return order.getItems();
    }

    public double getTotalPrice() {
        return order.getTotalPrice();
    }
}