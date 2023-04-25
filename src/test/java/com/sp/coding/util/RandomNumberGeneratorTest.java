package com.sp.coding.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomNumberGeneratorTest {

    @Test
    void generateContractRandomNumberShouldReturnFiveDigitNumber() {
        String randomNumber = RandomNumberGenerator.generateContractRandomNumber();
        assertTrue(randomNumber.matches("\\d{5}"), "Random number is not 5 digits long: " + randomNumber);
    }

}