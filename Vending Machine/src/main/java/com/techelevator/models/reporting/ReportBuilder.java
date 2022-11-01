package com.techelevator.models.reporting;

import com.techelevator.models.inventory.VendingItem;
import com.techelevator.models.inventory.VendingRow;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ReportBuilder {
    public static final int MAX_TRANSACTION_STRING_LENGTH = 17;
    public static final int MAX_SLOT_STRING_LENGTH = 3;
    public static final int MAX_AMOUNT_STRING_LENGTH = 9;
    String auditFilePath = "audit.txt";
    File auditFile = new File(auditFilePath);
    String reportFilePath = "sales.txt";
    File reportFile = new File(reportFilePath);

    public void writeLineToAuditFile(String transaction, String slot, BigDecimal beginAmount, BigDecimal endAmount) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(auditFile, true))) {
            // generate timestamp and format currency
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));
            String beginAmountAsString = String.format("$%.2f", beginAmount);
            String endAmountAsString = String.format("$%.2f", endAmount);
            String outputLine = timeStamp + " " + transaction;
            // add leading and trailing spaces for formatting columns
            for (int i = transaction.length(); i < MAX_TRANSACTION_STRING_LENGTH; i++) {
                outputLine += " ";
            }
            outputLine += slot;
            for (int i = slot.length(); i < MAX_SLOT_STRING_LENGTH; i++) {
                outputLine += " ";
            }
            for (int i = 0; i < MAX_AMOUNT_STRING_LENGTH - beginAmountAsString.length(); i++) {
                outputLine += " ";
            }
            outputLine += beginAmountAsString;
            for (int i = 0; i < MAX_AMOUNT_STRING_LENGTH - endAmountAsString.length(); i++) {
                outputLine += " ";
            }
            outputLine += endAmountAsString;
            writer.append(outputLine + "\n");

        } catch (FileNotFoundException e) {
            UserOutput.displayErrorMessage("AUDIT FILE NOT FOUND AT " + auditFile.getAbsolutePath());
        }
    }

    public void generateSalesReport(Map<String, VendingRow> inventory, BigDecimal totalSales) {
        try (PrintWriter writer = new PrintWriter(reportFile)) {
            Map<VendingItem, Integer> sales = new HashMap<>();
            writer.println("Taste Elevator Sales Report");
            // get item and quantity sold from each row
            for (Map.Entry<String, VendingRow> row : inventory.entrySet()) {
                // use getOrDefault to update rather than replace sales total if duplicate item exists in inventory
                sales.put(row.getValue().getItem(), row.getValue().getQuantitySold() +
                        sales.getOrDefault(row.getValue().getItem(), 0));
            }
            // print result to sales report
            for (Map.Entry<VendingItem, Integer> entry : sales.entrySet()) {
                String lineToPrint = entry.getKey().getName() + "|" + entry.getValue();
                writer.println(lineToPrint);
            }
            writer.println();
            writer.println("TOTAL SALES: " + String.format("$%.2f", totalSales));
        } catch (FileNotFoundException e) {
            UserOutput.displayErrorMessage("SALES REPORT FILE NOT FOUND AT " + reportFile.getAbsolutePath());
        }
    }
}
