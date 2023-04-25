package com.sp.coding.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceCalculatorTest {

    @Test
    void testCalculateMonthlyRate() {
        double netCapitalizedCost = 40000.0;
        double expectedMonthlyRate = 458.28;

        double actualMonthlyRate = PriceCalculator.calculateMonthlyRate(netCapitalizedCost);

        Assertions.assertEquals(expectedMonthlyRate, actualMonthlyRate, 0.01);
    }
}