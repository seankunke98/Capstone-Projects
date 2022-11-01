package com.techelevator;


import com.techelevator.application.VendingMachine;
import com.techelevator.models.reader.InventoryBuilder;

public class VendingMachineCLI {

    public static void main(String[] args) {
        InventoryBuilder inventoryBuilder = new InventoryBuilder();
        VendingMachine vendingMachine = new VendingMachine(inventoryBuilder);
        vendingMachine.run();
    }
}
