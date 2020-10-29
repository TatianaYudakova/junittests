package com.example.calculator;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.example.calculator.util.DynamicTestUtils.generateTests;


public class DynamicCalculatorTest {

    Calculator calculator = new Calculator();

    Path addNumbersCsvFile = Paths.get("src", "test", "resources", "addNumbers.csv");
    Path diffNumbersCsvFile = Paths.get("src", "test", "resources", "diffNumbers.csv");
    Path mulNumbersCsvFile = Paths.get("src", "test", "resources", "mulNumbers.csv");
    Path divNumbersCsvFile = Paths.get("src", "test", "resources", "divNumbers.csv");
    Path sumMod2NumbersCsvFile = Paths.get("src", "test", "resources", "sumMod2Numbers.csv");

    @TestFactory
    Stream<DynamicTest> testAddFactory() throws IOException {
        return generateTests(addNumbersCsvFile, "%s plus %s must be equal to %s", calculator::add);
    }

    @TestFactory
    Stream<DynamicTest> testDiffFactory() throws IOException {
        return generateTests(diffNumbersCsvFile, "%s minus %s must be equal to %s", calculator::diff);
    }

    @TestFactory
    Stream<DynamicTest> testMulFactory() throws IOException {
        return generateTests(mulNumbersCsvFile, "%s times %s must be equal to %s", calculator::mul);
    }

    @TestFactory
    Stream<DynamicTest> testDivFactory() throws IOException {
        return generateTests(divNumbersCsvFile, "%s divided by %s must be equal to %s", calculator::div);
    }

    @TestFactory
    Stream<DynamicTest> testSumMod2Factory() throws IOException {
        return generateTests(sumMod2NumbersCsvFile, "%s mod by %s must be equal to %s", calculator::sumMod2);
    }
}
