package it.academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 2: ASSERTIONS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="AssertionsDemo"
 *
 * JUnit 5 brings powerful new assertion methods:
 *
 * ★ assertAll()        — grouped assertions (JUnit 5 ONLY)
 * ★ assertThrows()     — lambda-based exception testing (JUnit 5 ONLY)
 * ★ assertTimeout()    — timeout assertion (JUnit 5 ONLY)
 * ★ assertTimeoutPreemptively() — preemptive timeout (JUnit 5 ONLY)
 *
 * In JUnit 4: exceptions were tested with @Test(expected=...) or @Rule ExpectedException.
 * In TestNG:  @Test(expectedExceptions=...).
 * JUnit 5's approach is FAR more flexible — you can assert the message, cause, etc.
 *
 * Also: JUnit 5 assertion messages are the LAST parameter (not first like JUnit 4).
 */
@DisplayName("Demo 2 — Assertions")
class AssertionsDemo {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ─── Standard assertions ───────────────────────────────────────────────────

    @Test
    @DisplayName("Standard assertions: assertEquals, assertTrue, assertFalse, assertNotNull")
    void standardAssertions() {
        assertEquals(4, calculator.add(2, 2));
        assertTrue(calculator.isPositive(5));
        assertFalse(calculator.isPositive(-1));
        assertNotNull(calculator);

        // JUnit 5: message is the LAST parameter (differs from JUnit 4 where it was first)
        assertEquals(10, calculator.multiply(2, 5), "2 × 5 should be 10");
    }

    // ─── assertAll — Grouped Assertions ────────────────────────────────────────

    /**
     * ★ JUNIT 5 ONLY — assertAll()
     *
     * Groups multiple assertions together. ALL assertions are evaluated even if
     * some fail — so you see ALL failures at once, not just the first one.
     *
     * NOT available in JUnit 4 or TestNG.
     * TestNG has SoftAssert but it's manual and error-prone.
     */
    @Test
    @DisplayName("★ assertAll — grouped assertions (JUnit 5 ONLY)")
    void groupedAssertions() {
        assertAll("Calculator operations",
                () -> assertEquals(5, calculator.add(2, 3)),
                () -> assertEquals(5, calculator.subtract(3, 2),"MESSAGE"),
                () -> assertEquals(6, calculator.multiply(2, 3)),
                () -> assertEquals(2, calculator.divide(6, 3))
        );
    }

    // ─── assertThrows — Exception Testing ──────────────────────────────────────

    /**

     * Lambda-based exception testing. Returns the exception instance so you
     * can inspect its message, cause, etc.
     *
     * JUnit 4 approach:  @Test(expected = ArithmeticException.class) — less flexible
     * TestNG approach:    @Test(expectedExceptions = ...) — also less flexible
     */
    @Test
    @DisplayName("★ assertThrows — exception testing ")
    void exceptionTesting() {
        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0),
                "Dividing by zero should throw ArithmeticException"
        );
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    @Test
    @DisplayName("assertDoesNotThrow — verifies no exception is thrown")
    void noExceptionTesting() {
        // ★ JUNIT 5 ONLY — assertDoesNotThrow
        assertDoesNotThrow(() -> calculator.divide(10, 2));
    }

    // ─── assertTimeout — Timeout Testing ───────────────────────────────────────

    /**
     * ★ JUNIT 5 ONLY — assertTimeout()
     *
     * Verifies that execution completes within a given duration.
     * The test is NOT aborted when timeout is exceeded — it runs to completion.
     *
     * JUnit 4 approach:  @Test(timeout = 1000) — aborts the test
     * TestNG approach:    @Test(timeOut = 1000) — aborts the test
     */
    @Test
    @DisplayName("★ assertTimeout — timeout assertion (JUnit 5 ONLY)")
    void timeoutTest() {
        assertTimeout(Duration.ofMillis(500), () -> {
            calculator.add(1, 1);
        });
    }

    /**
     * ★ JUNIT 5 ONLY — assertTimeoutPreemptively()
     *
     * Like assertTimeout but ABORTS execution if timeout is exceeded.
     * Runs the executable in a different thread.
     */
    @Test
    @DisplayName("★ assertTimeoutPreemptively — abortive timeout (JUnit 5 ONLY)")
    void timeoutPreemptivelyTest() {
        String result = assertTimeoutPreemptively(Duration.ofMillis(500), () -> {
            return calculator.greet("World");
        });
        assertEquals("Hello, World!", result);
    }

    // ─── assertIterableEquals ──────────────────────────────────────────────────

    /**
     * ★ JUNIT 5 ONLY — assertIterableEquals()
     * Compares two iterables element-by-element.
     */
    @Test
    @DisplayName("★ assertIterableEquals — compare collections (JUnit 5 ONLY)")
    void iterableEquals() {
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> actual = List.of(1, 2, 3);
        assertIterableEquals(expected, actual);
    }

    // ─── assertLinesMatch ──────────────────────────────────────────────────────

    /**
     * ★ JUNIT 5 ONLY — assertLinesMatch()
     * Compares lists of strings, supporting regex and fast-forward markers.
     */
    @Test
    @DisplayName("★ assertLinesMatch — compare string lists with regex (JUnit 5 ONLY)")
    void linesMatch() {
        List<String> expected = List.of("Hello, \\w+!", "Hello, \\w+!");
        List<String> actual = List.of("Hello, Alice!", "Hello, Bob!");
        assertLinesMatch(expected, actual);
    }
}

