package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coffee {

    int id;
    String name;
    double price;
    boolean sugar;
    boolean milk;
    int coffeeBeans;

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sugar=" + sugar +
                ", milk=" + milk +
                ", coffeeBeans=" + coffeeBeans +
                '}'+ '\n';
    }
}
