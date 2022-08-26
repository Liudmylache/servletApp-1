package com.example.demo;

public class Check {
    private int orderId;
    private String guestName;
    private int quantity;
    private String name;
    private double price;
    private boolean sugar;
    private boolean milk;
    private int coffeeBeans;
    private double total;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Check{" +
                "orderId=" + orderId +
                ", guestName='" + guestName + '\'' +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sugar=" + sugar +
                ", milk=" + milk +
                ", coffeeBeans=" + coffeeBeans +
                ", total=" + total +
                '}';
    }
}
