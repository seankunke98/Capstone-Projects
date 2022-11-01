package com.techelevator.models.inventory;

import java.math.BigDecimal;

public abstract class VendingItem {

    String name;
    BigDecimal price;
    String message;

    public VendingItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }
}
