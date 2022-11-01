package com.techelevator.models.transaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MoneyHandlerTest {
    MoneyHandler moneyHandler;

    @Before
    public void setup() {
        moneyHandler = new MoneyHandler();
    }

    @Test
    public void makeChange_returns_correct_change() {
        BigDecimal balance = new BigDecimal(5.65);
        Map<String, Integer> result = moneyHandler.makeChange(balance);
        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("Dollar", 5);
        expectedResult.put("Quarter", 2);
        expectedResult.put("Dime", 1);
        expectedResult.put("Nickel", 1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void add_balance_returns_correct_result() {
        BigDecimal result = moneyHandler.addToBalance(new BigDecimal(0), new BigDecimal(1));
        BigDecimal expected = new BigDecimal(1);
        Assert.assertEquals(expected, result);

    }

    @Test
    public void subtract_from_balance_returns_correct_amount() {
        BigDecimal result = moneyHandler.subtractFromBalance(new BigDecimal(5), new BigDecimal(2));
        BigDecimal expected = new BigDecimal(3);
        Assert.assertEquals(expected, result);
    }
}
