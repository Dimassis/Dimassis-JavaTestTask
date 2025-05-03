package model;

public class Drink implements MenuItem {
    private final String name;
    private final double price;
    private final int volumeMl;

    public Drink(String name, double price, int volumeMl) {
        this.name = name;
        this.price = price;
        this.volumeMl = volumeMl;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getVolumeMl() {
        return volumeMl;
    }
}