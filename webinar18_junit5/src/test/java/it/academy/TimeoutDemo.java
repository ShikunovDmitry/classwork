package it.academy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 11: TIMEOUTS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="TimeoutDemo"
 *
 * ★ JUNIT 5 ONLY — @Timeout annotation
 *
 * Declarative timeout for test methods, lifecycle methods, or entire classes.
 *
 * JUnit 4: @Test(timeout = 1000) — only on test methods, in milliseconds.
 * TestNG:  @Test(timeOut = 1000) — similar limitation.
 * JUnit 5: @Timeout — more flexible: supports multiple time units,
 *          applicable to classes, methods, and lifecycle methods.
 *
 * Also see assertTimeout() and assertTimeoutPreemptively() in Demo 2.
 */
@DisplayName("Demo 11 — ★ @Timeout (JUnit 5 ONLY)")
class TimeoutDemo {

    private final Calculator calculator = new Calculator();

    /**
     * ★ @Timeout — fails if the test takes longer than specified.
     * Default unit is SECONDS.
     */
    @Test
    @Timeout(5)
    @DisplayName("★ @Timeout(5) — must complete within 5 seconds")
    void defaultTimeoutInSeconds() {
        assertEquals(10, calculator.add(4, 6));
    }

    /**
     * ★ @Timeout with custom time unit.
     */
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @DisplayName("★ @Timeout with milliseconds")
    void timeoutInMilliseconds() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    /**
     * Combining @Timeout annotation with assertTimeout assertion.
     */
    @Test
    @Timeout(2)
    @DisplayName("Combining @Timeout with assertTimeout")
    void combinedTimeout() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            calculator.divide(100, 5);
        });
    }
}

