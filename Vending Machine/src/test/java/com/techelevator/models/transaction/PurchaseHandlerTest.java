package com.techelevator.models.transaction;

import com.techelevator.models.inventory.Gum;
import com.techelevator.models.inventory.VendingItem;
import com.techelevator.models.inventory.VendingRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class PurchaseHandlerTest {

    PurchaseHandler purchaseHandler;

    @Before
    public void setup() {
        purchaseHandler = new PurchaseHandler();
    }

    @Test
    public void make_purchase_returns_correct_valid_item() {
        Map<String, VendingRow> testInventory = new LinkedHashMap<>();
        testInventory.put("A1", new VendingRow(new Gum("testGum", new BigDecimal(1))));
        String resultName = purchaseHandler.makePurchase("A1", testInventory).getName();
        BigDecimal resultPrice = purchaseHandler.makePurchase("A1", testInventory).getPrice();

        Assert.assertEquals("testGum", resultName);
        Assert.assertEquals(new BigDecimal(1), resultPrice);
    }

    @Test
    public void make_purchase_returns_null_if_slot_does_not_exists() {
        Map<String, VendingRow> testInventory = new LinkedHashMap<>();
        testInventory.put("A1", new VendingRow(new Gum("testGum", new BigDecimal(1))));
        VendingItem result = purchaseHandler.makePurchase("A2", testInventory);
        Assert.assertNull(result);
    }

    @Test
    public void make_purchase_returns_null_if_row_quantity_is_zero() {
        Map<String, VendingRow> testInventory = new LinkedHashMap<>();
        testInventory.put("A1", new VendingRow(new Gum("testGum", new BigDecimal(1))));
        testInventory.get("A1").sellItem();
        testInventory.get("A1").sellItem();
        testInventory.get("A1").sellItem();
        testInventory.get("A1").sellItem();
        testInventory.get("A1").sellItem();
        testInventory.get("A1").sellItem();
        VendingItem result = purchaseHandler.makePurchase("A1", testInventory);

        Assert.assertNull(result);
    }
}
