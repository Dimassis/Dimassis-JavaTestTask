package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();
    private double discountPercent = 0.0;
    private boolean promoApplied = false;

    private static final Map<String, Double> PROMO_CODES = Map.of(
            "WELCOME10", 10.0,
            "VIP50", 50.0
    );



    public void addItem(Product product, int quantity) {
        items.merge(product, quantity, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscount(double percent) {
        this.discountPercent = percent;
    }

    public boolean applyPromoCode(String code) {
        if (promoApplied) {
            return false;
        }
        Double percent = PROMO_CODES.get(code.toUpperCase());
        if (percent != null) {
            this.discountPercent = percent;
            this.promoApplied = true;
            return true;
        }
        return false;
    }

    public boolean isPromoApplied() {
        return promoApplied;
    }

}