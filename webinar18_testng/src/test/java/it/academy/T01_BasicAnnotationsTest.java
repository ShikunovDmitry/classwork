package it.academy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 1: TestNG Basics — Annotations & Assertions                       ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  KEY CONCEPTS:                                                             ║
 * ║  • @Test annotation marks a method as a test                               ║
 * ║  • Assert class provides assertion methods                                 ║
 * ║  • No need to extend any base class (unlike JUnit 3)                       ║
 * ║                                                                            ║
 * ║  ┌─────────────────────────────────────────────────────────────────────┐    ║
 * ║  │  COMPARISON: TestNG vs JUnit 4 vs JUnit 5                         │    ║
 * ║  ├─────────────────┬─────────────────┬───────────────────────────────┤    ║
 * ║  │  TestNG          │  JUnit 4        │  JUnit 5                     │    ║
 * ║  ├─────────────────┼─────────────────┼───────────────────────────────┤    ║
 * ║  │ @Test            │ @Test           │ @Test                        │    ║
 * ║  │ (org.testng)     │ (org.junit)     │ (org.junit.jupiter.api)      │    ║
 * ║  │                  │                 │                               │    ║
 * ║  │ Assert.assertEquals │ Assert.assertEquals │ Assertions.assertEquals │    ║
 * ║  │ (actual, expected)  │ (expected, actual)  │ (expected, actual)      │    ║
 * ║  │ ⚠ NOTE: param   │                 │                               │    ║
 * ║  │   order differs! │                 │                               │    ║
 * ║  │                  │                 │                               │    ║
 * ║  │ No base class    │ No base class   │ No base class                │    ║
 * ║  │ needed           │ needed          │ needed                       │    ║
 * ║  └─────────────────┴─────────────────┴───────────────────────────────┘    ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T01_BasicAnnotationsTest {

    private final Calculator calc = new Calculator();

    /*
     * The simplest TestNG test. Just annotate a method with @Test.
     *
     * JUnit 4: identical — @org.junit.Test
     * JUnit 5: identical — @org.junit.jupiter.api.Test
     */
    @Test(groups = "lesson01")
    public void testAddition() {
        int result = calc.add(2, 3);
        // TestNG: Assert.assertEquals(actual, expected)
        // JUnit 4/5: Assert.assertEquals(expected, actual) — NOTE THE ORDER IS REVERSED!
        Assert.assertEquals(result, 5, "2 + 3 should equal 5");
    }

    @Test(groups = "lesson01")
    public void testSubtraction() {
        Assert.assertEquals(calc.subtract(10, 4), 6);
    }

    @Test(groups = "lesson01")
    public void testMultiplication() {
        Assert.assertEquals(calc.multiply(3, 7), 21);
    }

    @Test(groups = "lesson01")
    public void testDivision() {
        Assert.assertEquals(calc.divide(10, 2), 5.0);
    }
}

