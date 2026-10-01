package it.academy;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 10: DEPENDENCY INJECTION — TestInfo & TestReporter
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="TestInfoAndReporterDemo"
 *
 * ★ JUNIT 5 ONLY — Constructor and method dependency injection
 *
 * JUnit 5 supports injecting parameters into test constructors and methods:
 *   - TestInfo      — metadata about the current test (name, tags, display name)
 *   - TestReporter  — publish key-value entries to test reports
 *   - RepetitionInfo — see Demo 5 (Repeated Tests)
 *
 * NOT available in JUnit 4 (no DI support).
 * TestNG supports limited DI with ITestContext, but JUnit 5 is more extensible
 * via ParameterResolver extension.
 */
@DisplayName("Demo 10 — ★ TestInfo & TestReporter (JUnit 5 ONLY)")
class TestInfoAndReporterDemo {

    private final Calculator calculator = new Calculator();

    /**
     * ★ TestInfo — injected automatically, contains test metadata.
     */
    @Test
    @DisplayName("Test with TestInfo injection")
    @Tag("info-demo")
    void testWithTestInfo(TestInfo testInfo) {
        // Access test metadata
        String displayName = testInfo.getDisplayName();
        String methodName = testInfo.getTestMethod().orElseThrow().getName();
        var tags = testInfo.getTags();

        System.out.println("  Display name: " + displayName);
        System.out.println("  Method name:  " + methodName);
        System.out.println("  Tags:         " + tags);

        assertEquals("Test with TestInfo injection", displayName);
        assertTrue(tags.contains("info-demo"));
    }

    /**
     * ★ TestReporter — injected automatically, publishes entries to reports.
     * These entries appear in the test report output (not just stdout).
     */
    @Test
    @DisplayName("Test with TestReporter injection")
    void testWithTestReporter(TestReporter testReporter) {
        testReporter.publishEntry("status", "running");
        testReporter.publishEntry("operation", "addition");

        int result = calculator.add(10, 20);
        testReporter.publishEntry("result", String.valueOf(result));

        assertEquals(30, result);
    }

    /**
     * ★ Both TestInfo and TestReporter can be injected in the same method.
     */
    @Test
    @DisplayName("Combined injection")
    void combinedInjection(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("test", testInfo.getDisplayName());
        assertNotNull(testInfo);
        assertNotNull(testReporter);
    }
}
