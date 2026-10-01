package it.academy;

/**
 * Calculator – a simple utility class used throughout the JUnit 4 demo tests.
 * Provides basic arithmetic operations, string utilities, and methods that
 * intentionally throw exceptions so we can demonstrate exception-testing features.
 */
public class Calculator {

    /**
     * Adds two integers.
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts b from a.
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides a by b.
     *
     * @throws ArithmeticException if b is zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Returns the factorial of n.
     *
     * @throws IllegalArgumentException if n is negative
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Checks whether a number is even.
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Concatenates two strings with a space in between.
     */
    public String concatenate(String a, String b) {
        if (a == null || b == null) {
            throw new NullPointerException("Arguments must not be null");
        }
        return a + " " + b;
    }

    /**
     * A slow method that simulates a long computation (used to demo timeouts).
     */
    public int slowOperation() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 42;
    }
}
