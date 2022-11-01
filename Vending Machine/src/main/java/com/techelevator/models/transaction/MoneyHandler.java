package com.techelevator.models.transaction;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoneyHandler {

    public static final int VALUE_OF_DOLLAR_IN_CENTS = 100;
    public static final int VALUE_OF_QUARTER_IN_CENTS = 25;
    public static final int VALUE_OF_DIME_IN_CENTS = 10;
    public static final int VALUE_OF_NICKEL_IN_CENTS = 5;

    public BigDecimal addToBalance(BigDecimal balance, BigDecimal amountToAdd) {
        return balance.add(amountToAdd);
    }

    public BigDecimal subtractFromBalance(BigDecimal balance, BigDecimal amountToSubtract) {
        return balance.subtract(amountToSubtract);
    }

    public Map<String, Integer> makeChange(BigDecimal balance) {
        Map<String, Integer> change = new LinkedHashMap<>();
        // convert dollars to cents to take advantage of integer division
        int balanceAsCents = balance.multiply(new BigDecimal(100)).intValue();
        // divide balance by each denomination to determine quantity dispensed and update balance to remainder
        int dollars = balanceAsCents / VALUE_OF_DOLLAR_IN_CENTS;
        balanceAsCents = balanceAsCents % VALUE_OF_DOLLAR_IN_CENTS;
        int quarters = balanceAsCents / VALUE_OF_QUARTER_IN_CENTS;
        balanceAsCents = balanceAsCents % VALUE_OF_QUARTER_IN_CENTS;
        int dimes = balanceAsCents / VALUE_OF_DIME_IN_CENTS;
        balanceAsCents = balanceAsCents % VALUE_OF_DIME_IN_CENTS;
        int nickels = balanceAsCents / VALUE_OF_NICKEL_IN_CENTS;
        change.put("Dollar", dollars);
        change.put("Quarter", quarters);
        change.put("Dime", dimes);
        change.put("Nickel", nickels);
        return change;
    }
}
