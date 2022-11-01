package com.techelevator.ui;

import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * <p>
 * Dependencies: None
 */
public class UserInput {
    private Scanner scanner = new Scanner(System.in);

    public String getHomeScreenOption() {

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        if (option.equals("d")) {
            return "display";
        } else if (option.equals("p")) {
            return "purchase";
        } else if (option.equals("s")) {
            return "sales report";
        } else if (option.equals("e")) {
            return "exit";
        } else {
            return "invalid input";
        }

    }

    public String getPurchaseScreenOption() {
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        if (option.equals("m")) {
            return "feed money";
        } else if (option.equals("s")) {
            return "select";
        } else if (option.equals("f")) {
            return "finish";
        } else {
            return "invalid input";
        }
    }

    public String getMoneyScreenOption() {
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        if (option.equals("1")) {
            return "add one";
        } else if (option.equals("5")) {
            return "add five";
        } else if (option.equals("10")) {
            return "add ten";
        } else if (option.equals("20")) {
            return "add twenty";
        } else if (option.equals("r")) {
            return "return";
        } else {
            return "invalid amount";
        }
    }

    public String getPurchaseSelection() {
        String selectedOption = scanner.nextLine();
        return selectedOption.trim().toUpperCase();
    }

    public void pressEnterToContinue() {
        System.out.println();
        System.out.print("Press enter to continue");
        scanner.nextLine();
    }
}

