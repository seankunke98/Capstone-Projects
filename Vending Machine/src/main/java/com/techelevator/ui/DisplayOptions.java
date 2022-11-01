package com.techelevator.ui;

import java.util.ArrayList;
import java.util.List;

public abstract class DisplayOptions {
    private static List<String> displayOptions = new ArrayList<>();

    // static lists used to populate various menu screens
    public static List<String> Home() {
        displayOptions.clear();
        displayOptions.add("D) Display Items");
        displayOptions.add("P) Purchase");
        displayOptions.add("E) Exit");
        return displayOptions;
    }

    public static List<String> Purchase() {
        displayOptions.clear();
        displayOptions.add("M) Feed Money");
        displayOptions.add("S) Select Item");
        displayOptions.add("F) Finish Transaction");
        return displayOptions;
    }

    public static List<String> FeedMoney() {
        displayOptions.clear();
        displayOptions.add("1) Add $1.00");
        displayOptions.add("5) Add $5.00");
        displayOptions.add("10) Add $10.00");
        displayOptions.add("20) Add $20.00");
        displayOptions.add("R) Return to Purchase");
        return displayOptions;
    }
}
