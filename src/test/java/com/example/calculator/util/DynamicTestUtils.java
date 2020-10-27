package com.example.calculator.util;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestUtils {

    private DynamicTestUtils() {
    }

    public static Stream<DynamicTest> generateTests(Path csvFile, String format,
                                                    BiFunction<Integer, Integer, Integer> testMethod) throws IOException {
        return Files.lines(csvFile)
                .skip(1) // skip header
                .map(DynamicTestUtils::split)
                .map(DynamicTestUtils::parse)
                .map(nums -> {
                    int first = nums[0].maybeInt;
                    int second = nums[1].maybeInt;

                    if (nums[2].maybeInt != null) {
                        Integer expectedResult = nums[2].maybeInt;
                        String testDescription = String.format(format, first, second, expectedResult);
                        Executable doAssertion = () -> assertEquals(
                                expectedResult,
                                testMethod.apply(first, second),
                                testDescription
                        );
                        return dynamicTest(testDescription, doAssertion);
                    } else {
                        String errMsg = nums[2].message;
                        String testDescription = String.format(format, first, second, errMsg);
                        Executable doAssertion = () -> assertThrows(
                                Exception.class,
                                () -> testMethod.apply(first, second)
                        );
                        return dynamicTest(testDescription, doAssertion);
                    }
                });
    }

    private static String[] split(String line) {
        return line.split(",");
    }

    private static Pair[] parse(String[] nums) {
        return Stream.of(nums).map(s -> {
            Integer integer = null;
            try {
                integer = Integer.parseInt(s);
            } catch (NumberFormatException ignore) {
            }
            return new Pair(integer, s);
        }).toArray(Pair[]::new);
    }

    @FunctionalInterface
    public interface BiFunction<T, U, R> {
        R apply(T t, U u) throws Exception;
    }

    static final class Pair {
        public final Integer maybeInt;
        public final String message;

        public Pair(Integer maybeInt, String message) {
            this.maybeInt = maybeInt;
            this.message = message;
        }
    }
}
