package it.academy;

/**
 * Calculator — a simple class used as system-under-test (SUT) for JUnit 5 demos.
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public boolean isPositive(int number) {
        return number > 0;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        return "Hello, " + name + "!";
    }
}
