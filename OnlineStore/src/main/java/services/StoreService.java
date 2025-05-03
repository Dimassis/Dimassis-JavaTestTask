package services;

import models.Cart;
import models.CartItem;
import models.Product;

import java.util.List;
import java.util.Map;

public class StoreService {
    private final List<Product> catalog;
    private final Cart cart;

    public StoreService(List<Product> catalog) {
        this.catalog = catalog;
        this.cart = new Cart();
    }

    public void showCatalog() {
        for (Product p : catalog) {
            System.out.println(p.name() + " - " + p.price() + " руб.");
        }
    }

    public void addProductToCart(String name, int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Товаров не может быть меньше 1");
        }
        for (Product p : catalog) {
            if (p.name().equalsIgnoreCase(name)) {
                cart.addItem(p, quantity);
                System.out.println("Добавлено: " + name + " x" + quantity);
                return;
            }
        }
        System.out.println("Товар не найден: " + name);
    }

    public void printCart() {
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double totalPrice = product.price() * quantity;
            System.out.println(product.name() + " x" + quantity + " = " + totalPrice);
        }
        System.out.println("Итого со скидкой: " + calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            total += entry.getKey().price() * entry.getValue();
        }
        double discountAmount = total * cart.getDiscountPercent() / 100;
        if(discountAmount > total) {
            throw new IllegalArgumentException("Скидка не может быть больше цены товаров");
        }
        return total - discountAmount;
    }

    public void applyPromoCode(String code) {
        boolean result = cart.applyPromoCode(code);
        if (result) {
            System.out.println("Промокод применён: " + code + " (" + cart.getDiscountPercent() + "% скидка)");
        } else if (cart.isPromoApplied()) {
            System.out.println("Промокод уже был применён. Повторное применение невозможно.");
        } else {
            System.out.println("Недействительный промокод: " + code);
        }
    }

}