package com.example.demo;

public class Coffee {

    private int id;
    private String name;
    private double price;
    private boolean sugar;
    private boolean milk;
    private int coffeeBeans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public boolean isSugar() { return sugar; }

    public void setSugar(boolean sugar) { this.sugar = sugar; }

    public boolean isMilk() { return milk; }

    public void setMilk(boolean milk) { this.milk = milk; }

    public int getCoffeeBeans() { return coffeeBeans; }

    public void setCoffeeBeans(int coffeeBeans) { this.coffeeBeans = coffeeBeans; }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sugar=" + sugar +
                ", milk=" + milk +
                ", coffeeBeans=" + coffeeBeans +
                '}';
    }
}
