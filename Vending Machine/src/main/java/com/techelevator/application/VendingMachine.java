package com.techelevator.application;

import com.techelevator.models.inventory.VendingItem;
import com.techelevator.models.inventory.VendingRow;
import com.techelevator.models.reader.InventoryBuilder;
import com.techelevator.models.reporting.ReportBuilder;
import com.techelevator.models.transaction.MoneyHandler;
import com.techelevator.models.transaction.PurchaseHandler;
import com.techelevator.ui.DisplayOptions;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    UserInput userInput = new UserInput();
    UserOutput userOutput = new UserOutput();
    PurchaseHandler purchaseHandler = new PurchaseHandler();
    MoneyHandler moneyHandler = new MoneyHandler();
    ReportBuilder reportBuilder = new ReportBuilder();
    BigDecimal balance = new BigDecimal(0);
    BigDecimal totalSales = new BigDecimal(0);
    Map<String, VendingRow> inventory = new HashMap<>();

    public VendingMachine(InventoryBuilder inventoryBuilder) {
        inventory = inventoryBuilder.getInventory();
    }

    public void run() {
        while (true) {
            // display home screen and menu options and get input
            userOutput.displayHomeScreen();
            userOutput.displayScreenOptions(DisplayOptions.Home(), balance, false);
            String choice = userInput.getHomeScreenOption();
            userOutput.displayUserChoice(choice);

            if (choice.equals("display")) {
                // display the items
                userOutput.displayInventory(inventory);
                userInput.pressEnterToContinue();
            } else if (choice.equals("purchase")) {
                // make a purchase
                displayPurchaseMenu();
            } else if (choice.equals("sales report")) {
                // generate sales report
                reportBuilder.generateSalesReport(inventory, totalSales);
            } else if (choice.equals("exit")) {
                // goodbye
                break;
            }
        }
    }

    private void displayPurchaseMenu() {
        // display purchase menu options and get input
        userOutput.displayScreenOptions(DisplayOptions.Purchase(), balance, true);
        String choice = userInput.getPurchaseScreenOption();
        System.out.println(choice);
        userOutput.displayUserChoice(choice);

        if (choice.equals("feed money")) {
            displayFeedMoneyMenu();
        } else if (choice.equals("select")) {
            // display select menu and handle user choice
            userOutput.displayInventory(inventory);
            userOutput.promptUserForInput();
            choice = userInput.getPurchaseSelection();
            processPurchase(choice);
            userOutput.displayBalance(balance);
            displayPurchaseMenu();
        } else if (choice.equals("finish")) {
            // make change, update balance, and log to audit file
            userOutput.displayChange(moneyHandler.makeChange(balance));
            BigDecimal startingAmount = balance;
            balance = moneyHandler.subtractFromBalance(balance, balance);
            reportBuilder.writeLineToAuditFile("CHANGE GIVEN:", "", startingAmount, balance);
            userInput.pressEnterToContinue();
        }
    }

    private void processPurchase(String choice) {
        VendingItem item = purchaseHandler.makePurchase(choice, inventory);
        try {
            BigDecimal startingBalance = balance;
            // confirm funds available, process sale, and dispense item
            if (moneyHandler.subtractFromBalance(balance, item.getPrice()).compareTo(new BigDecimal(0)) >= 0) {
                balance = moneyHandler.subtractFromBalance(balance, item.getPrice());
                totalSales = moneyHandler.addToBalance(totalSales, item.getPrice());
                inventory.get(choice).sellItem();
                userOutput.dispenseItem(item);
                reportBuilder.writeLineToAuditFile(item.getName(), choice, startingBalance, balance);
            } else {
                UserOutput.displayErrorMessage("INSUFFICIENT FUNDS");
            }
        } catch (NullPointerException e) {
            UserOutput.displayErrorMessage("ITEM NOT AVAILABLE");
        }
    }

    private void displayFeedMoneyMenu() {
        // display feed money menu and get user choice
        userOutput.displayScreenOptions(DisplayOptions.FeedMoney(), balance, true);
        String choice = userInput.getMoneyScreenOption();
        userOutput.displayUserChoice(choice);

        // handle money fed into machine
        BigDecimal amountToAdd = new BigDecimal(0);
        if (choice.equals("add one")) {
            amountToAdd = new BigDecimal(1.0);
            balance = moneyHandler.addToBalance(balance, amountToAdd);
        } else if (choice.equals("add five")) {
            amountToAdd = new BigDecimal(5.0);
            balance = moneyHandler.addToBalance(balance, amountToAdd);
        } else if (choice.equals("add ten")) {
            amountToAdd = new BigDecimal(10.0);
            balance = moneyHandler.addToBalance(balance, amountToAdd);
        } else if (choice.equals("add twenty")) {
            amountToAdd = new BigDecimal(20.0);
            balance = moneyHandler.addToBalance(balance, amountToAdd);
        } else if (choice.equals("return")) {
            displayPurchaseMenu();
            return;
        }

        // write line to audit file and return to feed money menu
        reportBuilder.writeLineToAuditFile("MONEY FED:", "", amountToAdd, balance);
        displayFeedMoneyMenu();
    }
}
