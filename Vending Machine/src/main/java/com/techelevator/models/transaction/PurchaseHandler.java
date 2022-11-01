package com.techelevator.models.transaction;

import com.techelevator.models.inventory.VendingItem;
import com.techelevator.models.inventory.VendingRow;

import java.util.Map;

public class PurchaseHandler {
    public VendingItem makePurchase(String choice, Map<String, VendingRow> inventory) {
        // return null if item is sold out or does not exist
        if (!inventory.containsKey(choice)) {
            return null;
        } else if (inventory.get(choice).getQuantity() < 1) {
            return null;
        } else {
            return inventory.get(choice).getItem();
        }
    }
}
