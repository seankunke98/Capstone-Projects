package com.techelevator.models.reader;

import com.techelevator.models.inventory.VendingRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class InventoryBuilderTest {
    InventoryBuilder inventoryBuilder;

    @Before
    public void setup() {
        inventoryBuilder = new InventoryBuilder();
    }

    @Test
    public void get_inventory_returns_correct_inventory() {
        inventoryBuilder.setFilePath("testFile1.csv");
        Map<String, VendingRow> testMap = inventoryBuilder.getInventory();
        int counter = 0;
        for (Map.Entry<String, VendingRow> current : testMap.entrySet()) {
            counter++;
        }
        Assert.assertEquals(16, counter);
    }

    @Test
    public void get_inventory_correctly_ignores_bad_item_types() {
        inventoryBuilder.setFilePath("testFile2.csv");
        Map<String, VendingRow> testMap = inventoryBuilder.getInventory();
        int counter = 0;
        for (Map.Entry<String, VendingRow> current : testMap.entrySet()) {
            counter++;
        }
        Assert.assertEquals(14, counter);
    }
}
