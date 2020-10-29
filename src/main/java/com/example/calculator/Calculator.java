package com.example.calculator;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int diff(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) throws Exception {
        if (b == 0) {
            throw new Exception("Деление на 0");
        }

        return a / b;
    }

    public int sumMod2(int a, int b) {
        return a ^ b;
    }
}
