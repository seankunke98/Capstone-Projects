package com.techelevator.ui;

import com.techelevator.models.inventory.VendingItem;
import com.techelevator.models.inventory.VendingRow;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UserOutput {

    public static final int LENGTH_OF_APPLICATION_DISPLAY = 52;

    public static void displayErrorMessage(String message) {
        System.out.println();
        System.out.println("****************************************************");
        // add leading spaces to center heading text
        for (int i = 0; i < LENGTH_OF_APPLICATION_DISPLAY / 2 - message.length() / 2; i++) {
            System.out.print(" ");
        }
        System.out.println(message);
        System.out.println("****************************************************");
        System.out.println();
    }

    public void displayHomeScreen() {
        System.out.println();
        System.out.println("****************************************************");
        System.out.println("                        Home");
        System.out.println("****************************************************");
        System.out.println();
    }

    public void displayScreenOptions(List<String> displayOptions, BigDecimal balance, boolean displayBalance) {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println();

        // print display options from static list to screen
        for (String displayOption : displayOptions) {
            System.out.println(displayOption);
        }

        if (displayBalance) {
            displayBalance(balance);
        }

        promptUserForInput();
    }

    public void promptUserForInput() {
        System.out.println();
        System.out.print("Please select an option: ");
    }

    public void displayUserChoice(String choice) {
        System.out.println();
        System.out.println("****************************************************");
        // add leading spaces to center heading text
        for (int i = 0; i < LENGTH_OF_APPLICATION_DISPLAY / 2 - choice.length() / 2; i++) {
            System.out.print(" ");
        }
        System.out.println(choice.toUpperCase());
        System.out.println("****************************************************");
        System.out.println();
    }

    public void displayBalance(BigDecimal balance) {
        System.out.println();
        System.out.println("Current Money Provided: " + String.format("$%.2f", balance));
        System.out.println();
    }

    public void displayInventory(Map<String, VendingRow> inventory) {
        for (Map.Entry<String, VendingRow> entry : inventory.entrySet()) {
            // generate display string, check quantity remaining, and update display string accordingly
            String displayMessage = "[" + entry.getKey() + "] " + entry.getValue().getItem().getName() + " : " +
                    String.format("$%.2f", entry.getValue().getItem().getPrice()) + " : ";
            if (entry.getValue().getQuantity() < 1) {
                displayMessage += "NO LONGER AVAILABLE";
            } else {
                displayMessage += entry.getValue().getQuantity() + " remaining";
            }
            System.out.println(displayMessage);
        }
    }

    public void displayChange(Map<String, Integer> change) {
        System.out.println();
        System.out.println("Dispensing change: ");
        // print the number of coins of each denomination returned
        for (Map.Entry<String, Integer> entry : change.entrySet()) {
            String denomination = entry.getKey();
            Integer value = entry.getValue();
            if (value != 1) {
                denomination += "s";
            }
            System.out.println(value + " " + denomination);
        }
    }

    public void dispenseItem(VendingItem item) {
        String output = item.toString();
        System.out.println();
        System.out.println("****************************************************");
        System.out.println("Dispensing Selection:");
        System.out.println(output);
        System.out.println("****************************************************");
        System.out.println();
    }
}
