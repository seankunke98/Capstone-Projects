package com.techelevator.models.reporting;

import com.techelevator.models.inventory.Gum;
import com.techelevator.models.inventory.VendingRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ReportBuilderTest {

    ReportBuilder reportBuilder;

    @Before
    public void setup() {
        reportBuilder = new ReportBuilder();
    }

    @Test
    public void assert_audit_file_exists_after_writing() {
        reportBuilder.writeLineToAuditFile("MONEY FED", "", new BigDecimal(0), new BigDecimal(5));
        File file = new File("audit.txt");
        boolean result = file.exists();
        Assert.assertTrue("Audit file should create", result);
    }

    @Test
    public void assert_sales_report_file_exists_after_writing() {
        Map<String, VendingRow> testInventory = new LinkedHashMap<>();
        reportBuilder.generateSalesReport(testInventory, new BigDecimal(100));
        File file = new File("sales.txt");
        boolean result = file.exists();
        Assert.assertTrue("Sales file should create", result);
    }

    @Test
    public void audit_file_line_matches_expected_line() {
        reportBuilder.writeLineToAuditFile("MONEY FED", "", new BigDecimal(0), new BigDecimal(10));
        String expected = "MONEY FED               $0.00   $10.00";
        String result = "";
        File file = new File("audit.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        result = result.substring(23);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void sales_report_line_matches_expected_line() {
        Map<String, VendingRow> testMap = new LinkedHashMap<>();
        testMap.put("A1", new VendingRow(new Gum("testGum", new BigDecimal(1))));
        reportBuilder.generateSalesReport(testMap, new BigDecimal(0));
        String expected = "testGum|0";
        String result = "";
        File file = new File("sales.txt");
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            result = scanner.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expected, result);

    }
}
