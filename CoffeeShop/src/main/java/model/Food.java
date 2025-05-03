package model;

public class Food implements MenuItem {
    private final String name;
    private final double price;
    private final int calories;

    public Food(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }
}