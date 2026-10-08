package it.academy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 16: TAGGING, FILTERING & COMPOSED ANNOTATIONS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="TaggingAndFilteringDemo"
 *
 * @Tag is used to filter tests at build time.
 *   mvn test -Dgroups="fast"              — run only @Tag("fast") tests
 *   mvn test -DexcludedGroups="slow"      — exclude @Tag("slow") tests
 *
 * JUnit 4: @Category — similar but required interfaces.
 * TestNG:  @Test(groups = {"fast"}) — similar concept.
 *
 * ★ JUNIT 5 ONLY — Composed/Meta-Annotations
 *
 * You can create CUSTOM annotations that COMBINE multiple JUnit 5 annotations.
 * NOT available in JUnit 4 or TestNG.
 */
@DisplayName("Demo 16 — Tags & ★ Composed Annotations")
class TaggingAndFilteringDemo {

    private final Calculator calculator = new Calculator();

    // ─── Standard tagging ──────────────────────────────────────────────────────

    @Test
    @Tag("fast")
    @DisplayName("Fast test — addition")
    void fastTest() {
        assertEquals(10, calculator.add(4, 6));
    }

    @Test
    @Tag("slow")
    @DisplayName("Slow test — simulated heavy operation")
    void slowTest() throws InterruptedException {
        Thread.sleep(100);  // Simulate slow operation
        assertEquals(100, calculator.multiply(10, 10));
    }

    // ─── ★ Composed/Meta-Annotations ──────────────────────────────────────────

    /**
     * ★ JUNIT 5 ONLY — Custom composed annotation.
     *
     * Combines @Test, @Tag, and @DisplayName into a single reusable annotation.
     * This reduces boilerplate and enforces consistent tagging.
     *
     * NOT available in JUnit 4 or TestNG.
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @Tag("smoke")
    @Tag("fast")
    @interface SmokeTest {
    }

    /** Custom composed annotation for integration tests. */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @Tag("integration")
    @Tag("slow")
    @interface IntegrationTest {
    }

    // ─── Using composed annotations ────────────────────────────────────────────

    @SmokeTest  // ★ Custom annotation — replaces @Test + @Tag("smoke") + @Tag("fast")
    @DisplayName("★ Smoke test using composed annotation")
    void smokeTest() {
        assertNotNull(calculator);
        assertEquals(5, calculator.add(2, 3));
    }

    @IntegrationTest  // ★ Custom annotation
    @DisplayName("★ Integration test using composed annotation")
    void integrationTest() {
        // Simulate integration test
        String greeting = calculator.greet("System");
        assertTrue(greeting.startsWith("Hello"));
    }
}

