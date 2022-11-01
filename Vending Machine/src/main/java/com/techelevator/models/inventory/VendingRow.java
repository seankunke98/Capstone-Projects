package com.techelevator.models.inventory;

public class VendingRow {

    VendingItem item;
    int quantity;
    int maxQuantity = 6;

    public VendingRow(VendingItem item) {
        this.item = item;
        quantity = maxQuantity;
    }

    public void sellItem() {
        quantity -= 1;
    }

    public int getQuantitySold() {
        return getMaxQuantity() - getQuantity();
    }

    public VendingItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }
}
