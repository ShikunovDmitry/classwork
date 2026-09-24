package it.academy;

/**
 * Simple Calculator class used as a System Under Test (SUT)
 * for demonstrating TestNG features.
 */
public class Calculator {

    // ---- Arithmetic operations ----

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) a / b;
    }

    // ---- String operations ----

    public String concatenate(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        return a + b;
    }

    public boolean isPalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text must not be null");
        }
        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }

    // ---- Collection / utility operations ----

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("TestNG Demo Project — IT Academy");
    }
}
