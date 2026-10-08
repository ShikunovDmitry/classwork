package it.academy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 8: CONDITIONAL TEST EXECUTION & ASSUMPTIONS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="ConditionalTestsDemo"
 *
 * ★ JUNIT 5 ONLY — Rich set of conditional annotations:
 *
 *   @EnabledOnOs / @DisabledOnOs              — by operating system
 *   @EnabledOnJre / @DisabledOnJre            — by JRE version
 *   @EnabledForJreRange / @DisabledForJreRange — by JRE range
 *   @EnabledIfSystemProperty                  — by system property
 *   @EnabledIfEnvironmentVariable             — by env variable
 *   @EnabledIf / @DisabledIf                  — by custom condition
 *
 * JUnit 4: Only assumeTrue/assumeFalse (basic assumptions).
 * TestNG:  No built-in OS/JRE conditions. Uses @Test(enabled=...) or listeners.
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 * ASSUMPTIONS (available in JUnit 4 too, but enhanced in JUnit 5)
 *
 * assumeTrue/assumeFalse — skip test if assumption fails (not a failure!)
 * ★ assumingThat()       — execute part of a test conditionally (JUnit 5 ONLY)
 */
@DisplayName("Demo 8 — ★ Conditional Tests & Assumptions")
class ConditionalTestsDemo {

    private final Calculator calculator = new Calculator();

    // ─── OS-based conditions ───────────────────────────────────────────────────

    /**
     * ★ @EnabledOnOs — runs only on specified OS.
     * NOT available in JUnit 4 or TestNG.
     */
    @Test
    @EnabledOnOs(OS.MAC)
    @DisplayName("★ Runs only on macOS")
    void onlyOnMac() {
        assertTrue(calculator.add(1, 1) == 2);
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    @DisplayName("★ Disabled on Windows")
    void notOnWindows() {
        assertNotNull(calculator);
    }

    // ─── JRE-based conditions ──────────────────────────────────────────────────

    /**
     * ★ @EnabledOnJre — runs only on specified JRE version.
     * NOT available in JUnit 4 or TestNG.
     */
    @Test
    @EnabledOnJre(JRE.JAVA_17)
    @DisplayName("★ Runs only on JRE 17")
    void onlyOnJava17() {
        assertTrue(true);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_21)
    @DisplayName("★ Runs on JRE 11 to 21")
    void onJava11to21() {
        assertTrue(true);
    }

    // ─── System property conditions ────────────────────────────────────────────

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @DisplayName("★ Runs only on 64-bit architecture")
    void only64Bit() {
        assertTrue(true);
    }

    // ─── Environment variable conditions ───────────────────────────────────────

    @Test
    @EnabledIfEnvironmentVariable(named = "PATH", matches = ".*")
    @DisplayName("★ Runs only when PATH env variable exists")
    void pathExists() {
        assertTrue(true);
    }

    // ─── Assumptions ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("assumeTrue — skip if assumption fails")
    void assumptionTest() {
        // If this assumption fails, the test is SKIPPED (not failed)
        assumeTrue(System.getProperty("os.name").contains("Mac"),
                "Test skipped — not running on macOS");
        assertEquals(4, calculator.add(2, 2));
    }

    /**
     * ★ JUNIT 5 ONLY — assumingThat()
     *
     * Executes a block of assertions ONLY IF the assumption is true.
     * The rest of the test still runs regardless.
     */
    @Test
    @DisplayName("★ assumingThat — conditional assertion block (JUnit 5 ONLY)")
    void assumingThatTest() {
        // This assertion always runs
        assertEquals(4, calculator.add(2, 2));

        // This block runs ONLY if we're on macOS
        assumingThat(
                System.getProperty("os.name").contains("Mac"),
                () -> {
                    System.out.println("  Running macOS-specific assertions");
                    assertEquals(6, calculator.multiply(2, 3));
                }
        );

        // This assertion always runs regardless of the assumption
        assertEquals(0, calculator.multiply(0, 100));
    }
}

