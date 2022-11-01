package com.techelevator.models.inventory;

import java.math.BigDecimal;

public class Gum extends VendingItem {

    public Gum(String name, BigDecimal price) {
        super(name, price);
        message = "Chewy, Chewy, Lots O Bubbles!";
    }

    @Override
    public String toString() {
        return name + ", " + String.format("$%.2f", price) + ", " + message;
    }
}
