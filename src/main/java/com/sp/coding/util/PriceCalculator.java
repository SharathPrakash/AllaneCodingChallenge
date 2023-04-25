package com.sp.coding.util;

public class PriceCalculator {
    private static final double INTEREST_RATE = 4.0;
    private static final double LEASE_TERM = 36.0;

    public static double calculateMonthlyRate(double netCapitalizedCost) {
        double residualValue = netCapitalizedCost * 0.6;
        double moneyFactor = INTEREST_RATE / 2400.0;
        double monthlyPayment = (netCapitalizedCost - residualValue) * moneyFactor / (1 - Math.pow(1 + moneyFactor, -LEASE_TERM));
        return monthlyPayment;
    }
}
