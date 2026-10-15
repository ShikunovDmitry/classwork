package it.academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 5: REPEATED TESTS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="RepeatedTestsDemo"
 *
 * ★ JUNIT 5 ONLY — @RepeatedTest
 *
 * Runs the same test multiple times. Useful for:
 *   - Testing idempotency
 *   - Detecting flaky tests
 *   - Stress testing
 *
 * NOT available in JUnit 4.
 * TestNG has @Test(invocationCount = N) — similar but JUnit 5's RepetitionInfo
 * provides richer metadata.
 *
 * Each repetition gets a fresh @BeforeEach / @AfterEach lifecycle.
 */
@DisplayName("Demo 5 — ★ Repeated Tests (JUnit 5 ONLY)")
class RepeatedTestsDemo {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /**
     * ★ @RepeatedTest — repeats the test 3 times.
     * Default display: "repetition X of Y".
     */
    @RepeatedTest(3)
    @DisplayName("Addition is consistent")
    void repeatedAddition() {
        assertEquals(4, calculator.add(2, 2));
    }

    /**
     * ★ @RepeatedTest with custom display name.
     * Placeholders: {displayName}, {currentRepetition}, {totalRepetitions}.
     */
    @RepeatedTest(value = 5, name = "{displayName} — run {currentRepetition}/{totalRepetitions}")
    @DisplayName("Multiply by zero")
    void repeatedMultiplyByZero() {
        assertEquals(0, calculator.multiply(42, 0));
    }

    /**
     * ★ RepetitionInfo — injectable parameter with repetition metadata.
     * NOT available in JUnit 4 or TestNG.
     */
    @RepeatedTest(3)
    @DisplayName("Using RepetitionInfo")
    void withRepetitionInfo(RepetitionInfo repetitionInfo) {
        int current = repetitionInfo.getCurrentRepetition();
        int total = repetitionInfo.getTotalRepetitions();
        System.out.printf("  Repetition %d of %d%n", current, total);

        assertTrue(current <= total);
        assertTrue(calculator.isPositive(current));
    }
}

