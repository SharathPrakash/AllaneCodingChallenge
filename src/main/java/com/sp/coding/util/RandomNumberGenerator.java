package com.sp.coding.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

    public static String generateContractRandomNumber() {
        int min = 10000;
        int max = 99999;
        return "" + ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
