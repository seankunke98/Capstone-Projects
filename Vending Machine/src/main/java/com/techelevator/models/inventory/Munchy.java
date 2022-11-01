package com.techelevator.models.inventory;

import java.math.BigDecimal;

public class Munchy extends VendingItem {

    public Munchy(String name, BigDecimal price) {
        super(name, price);
        message = "Munchy, Munchy, so Good!";
    }

    @Override
    public String toString() {
        return name + ", " + String.format("$%.2f", price) + ", " + message;
    }
}
