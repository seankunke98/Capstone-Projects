package com.techelevator.models.inventory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingRowTest {

    VendingRow vendingRow;
    VendingItem vendingItem = new Gum("testGum", new BigDecimal(1));

    @Before
    public void setup() {
        vendingRow = new VendingRow(vendingItem);

    }

    @Test
    public void vending_row_initializes_quantity_correctly() {
        int expected = 6;
        int actual = vendingRow.getQuantity();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sell_item_correctly_reduces_quantity() {
        int expected = 5;
        vendingRow.sellItem();
        int actual = vendingRow.getQuantity();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get_quantity_sold_returns_correct_quantity() {
        int expected = 3;
        vendingRow.sellItem();
        vendingRow.sellItem();
        vendingRow.sellItem();
        int actual = vendingRow.getQuantitySold();
        Assert.assertEquals(expected, actual);
    }
}
