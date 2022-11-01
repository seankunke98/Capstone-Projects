package com.techelevator.models.inventory;

import java.math.BigDecimal;

public class Drink extends VendingItem {

    public Drink(String name, BigDecimal price) {
        super(name, price);
        message = "Drinky, Drinky, Slurp Slurp!";
    }

    @Override
    public String toString() {
        return name + ", " + String.format("$%.2f", price) + ", " + message;
    }
}
