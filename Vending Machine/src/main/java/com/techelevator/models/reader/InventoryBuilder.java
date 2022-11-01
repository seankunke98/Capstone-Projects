package com.techelevator.models.reader;

import com.techelevator.models.inventory.*;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class InventoryBuilder {

    public static final String FILE_DELIMITER = ",";
    public static final String MUNCHY = "munchy";
    public static final String CANDY = "candy";
    public static final String DRINK = "drink";
    public static final String GUM = "gum";
    private String filePath = "catering.csv";

    public Map<String, VendingRow> getInventory() {
        String filePath = this.filePath;
        File inventoryFile = new File(filePath);
        Map<String, VendingRow> inventory = new TreeMap<>();

        // read inventory from csv file and add to inventory map
        try (Scanner reader = new Scanner(inventoryFile)) {
            while (reader.hasNextLine()) {
                String[] itemFields = reader.nextLine().split(FILE_DELIMITER);
                String slot = itemFields[0].toUpperCase().trim();
                String itemType = itemFields[3].toLowerCase().trim();
                String itemName = itemFields[1].trim();
                BigDecimal itemPrice = new BigDecimal(itemFields[2].trim());
                VendingItem item;
                if (itemType.equals(MUNCHY)) {
                    item = new Munchy(itemName, itemPrice);
                } else if (itemType.equals(CANDY)) {
                    item = new Candy(itemName, itemPrice);
                } else if (itemType.equals(DRINK)) {
                    item = new Drink(itemName, itemPrice);
                } else if (itemType.equals(GUM)) {
                    item = new Gum(itemName, itemPrice);
                } else {
                    UserOutput.displayErrorMessage("UNABLE TO LOAD ITEM " + itemName + " IN SLOT " + slot);
                    continue;
                }
                inventory.put(slot, new VendingRow(item));
            }
        } catch (FileNotFoundException e) {
            UserOutput.displayErrorMessage("CANNOT FIND INVENTORY FILE AT " + inventoryFile.getAbsolutePath());
        }

        return inventory;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
