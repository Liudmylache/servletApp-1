package com.example.demo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Check {
    private int orderId;
    private String guestName;
    private int quantity;
    private int coffeeId;
    private double total;


    @Override
    public String toString() {
        return "Check{" +
                "orderId=" + orderId +
                ", guestName='" + guestName + '\'' +
                ", quantity=" + quantity +
                ", coffeeId='" + coffeeId + '\'' +
                ", total=" + total +
                '}' + '\n';
    }
}
