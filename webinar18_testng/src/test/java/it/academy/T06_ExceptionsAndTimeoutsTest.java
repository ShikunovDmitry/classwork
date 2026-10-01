package it.academy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 5: Expected Exceptions & Timeouts                                 ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON: Exception Testing                                  │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ @Test(expect-│ @Test(expected=  │ assertThrows(Exception.class, │      ║
 * ║  │ edExceptions │ Exception.class) │   () -> { ... })              │      ║
 * ║  │ = {Ex.class})│                  │                               │      ║
 * ║  │              │ @Rule            │ ⚠ JUnit 5 assertThrows is    │      ║
 * ║  │              │ ExpectedException│ more powerful — you can       │      ║
 * ║  │              │                  │ inspect the exception object  │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON: Timeout Testing                                    │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ @Test(timeout│ @Test(timeout=   │ assertTimeout(Duration.of-    │      ║
 * ║  │ = 1000)      │ 1000)            │  Millis(1000), () -> {...})   │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ Also:        │                  │ assertTimeoutPreemptively()   │      ║
 * ║  │ timeOut at   │                  │ — aborts if exceeded          │      ║
 * ║  │ suite level  │                  │                               │      ║
 * ║  │ in xml       │                  │                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T06_ExceptionsAndTimeoutsTest {

    private final Calculator calc = new Calculator();

    // ─────────── EXPECTED EXCEPTIONS ───────────
    // Test passes ONLY if the specified exception is thrown.

    @Test(expectedExceptions = ArithmeticException.class, groups = "lesson05")
    public void testDivideByZeroThrowsException() {
        // JUnit 4: @Test(expected = ArithmeticException.class)
        // JUnit 5: assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
        calc.divide(10, 0);
    }

    // ─────────── EXPECTED EXCEPTION WITH MESSAGE VALIDATION ───────────
    // expectedExceptionsMessageRegExp lets you validate the exception message.
    // JUnit 4: requires @Rule ExpectedException for message checking.
    // JUnit 5: Throwable ex = assertThrows(...); assertEquals("msg", ex.getMessage());

    @Test(
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = ".*must not be null.*",
            groups = "lesson05"
    )
    public void testConcatenateNullThrowsWithMessage() {
        calc.concatenate(null, "world");
    }

    @Test(
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "n must be >= 0",
            groups = "lesson05"
    )
    public void testFactorialNegativeThrows() {
        calc.factorial(-1);
    }

    // ─────────── TIMEOUT ───────────
    // Test fails if it takes longer than the specified time (in ms).

    @Test(timeOut = 1000, groups = "lesson05")
    public void testPerformanceUnderOneSecond() {
        // This test must complete within 1000ms
        int sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
        Assert.assertTrue(sum > 0);
    }

    // ─────────── TESTING EXCEPTION MANUALLY (Alternative approach) ───────────
    // Similar to JUnit 5's assertThrows() pattern

    @Test(groups = "lesson05")
    public void testExceptionManually() {
        try {
            calc.divide(1, 0);
            Assert.fail("Expected ArithmeticException was not thrown");
        } catch (ArithmeticException e) {
            Assert.assertEquals(e.getMessage(), "Cannot divide by zero");
        }
    }
}

