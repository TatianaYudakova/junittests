package com.example.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "0,    1,   -1",
            "1,    2,  -1",
            "64,  51, 13",
            "165,  100, 65"
    })
    void diff(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.diff(first, second),
                () -> first + " - " + second + " should equal " + expectedResult);
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "0,    1,  0",
            "1,    2,  2",
            "5,  8, 40",
            "56,  10, 560"
    })
    void mul(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.mul(first, second),
                () -> first + " * " + second + " should equal " + expectedResult);
    }

    @ParameterizedTest(name = "divided by zero")
    @CsvSource({
            "1,    0,   Деление на 0"
    })
    void badDiv(int first, int second, String exception) {
        Calculator calculator = new Calculator();
        final Exception exceptionMessage = assertThrows(Exception.class,
                () -> calculator.div(first, second));
        assertEquals(exceptionMessage.getMessage(),"Деление на 0");
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "1,   1,   1",
            "4,   2,   2",
            "100, 10,  10"
    })
    void div(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        try {
            assertEquals(expectedResult, calculator.div(first, second),
                    () -> first + " / " + second + " should equal " + expectedResult);
        } catch (Exception e) {

        }
    }
}
