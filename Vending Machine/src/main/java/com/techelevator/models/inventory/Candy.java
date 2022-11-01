package com.techelevator.models.inventory;

import java.math.BigDecimal;

public class Candy extends VendingItem {

    public Candy(String name, BigDecimal price) {
        super(name, price);
        message = "Sugar, Sugar, so Sweet!";
    }

    @Override
    public String toString() {
        return name + ", " + String.format("$%.2f", price) + ", " + message;
    }
}
