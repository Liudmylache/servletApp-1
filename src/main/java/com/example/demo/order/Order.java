package com.example.demo.order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private int entryId;
    private int checkId;
    private int coffeeId;
    private int quantity;
    private double itemPrice;
    private double entryTotal;


    @Override
    public String toString() {
        return "Order{" +
                "entryId=" + entryId +
                ", checkId=" + checkId +
                ", coffeeId=" + coffeeId +
                ", quantity=" + quantity +
                ", itemPrice=" + itemPrice +
                ", entryTotal=" + entryTotal +
                '}' + '\n';
    }
}
