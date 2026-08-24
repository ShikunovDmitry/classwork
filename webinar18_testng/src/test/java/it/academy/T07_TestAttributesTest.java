package it.academy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 6: @Test Attributes — enabled, priority, description              ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG @Test annotation has many useful attributes:                       ║
 * ║  • enabled = false   → skip/disable a test without deleting it             ║
 * ║  • priority = N      → control execution order (lower runs first)          ║
 * ║  • description = ""  → add documentation visible in reports                ║
 * ║  • invocationCount   → run the same test N times                           ║
 * ║  • threadPoolSize    → run invocations in parallel threads                 ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ enabled=false│ @Ignore          │ @Disabled                     │      ║
 * ║  │              │ (no reason msg)  │ @Disabled("reason")           │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ priority=N   │ — (none)         │ @Order(N) (with               │      ║
 * ║  │              │                  │  @TestMethodOrder)             │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ description  │ — (none)         │ @DisplayName("...")           │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ invocation-  │ — (none, need    │ @RepeatedTest(N)              │      ║
 * ║  │ Count=N      │  external runner)│                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T07_TestAttributesTest {

    private final Calculator calc = new Calculator();

    // ─────────── DISABLED TEST ───────────
    // enabled = false → test is skipped.
    // JUnit 4: @Ignore
    // JUnit 5: @Disabled("reason")

    @Test(enabled = false, groups = "lesson06")
    public void disabledTest() {
        // This test will NOT run. Use this to temporarily disable a test.
        Assert.fail("This should never execute");
    }

    // ─────────── PRIORITY ───────────
    // Lower priority number runs first. Default priority is 0.

    @Test(priority = 3, groups = "lesson06", description = "Runs third due to priority=3")
    public void testC_Priority3() {
        System.out.println("  Priority 3 — executed third");
        Assert.assertTrue(true);
    }

    @Test(priority = 1, groups = "lesson06", description = "Runs first due to priority=1")
    public void testA_Priority1() {
        System.out.println("  Priority 1 — executed first");
        Assert.assertTrue(true);
    }

    @Test(priority = 2, groups = "lesson06", description = "Runs second due to priority=2")
    public void testB_Priority2() {
        System.out.println("  Priority 2 — executed second");
        Assert.assertTrue(true);
    }

    // ─────────── DESCRIPTION ───────────
    // description appears in TestNG reports (HTML, XML).
    // JUnit 5 equivalent: @DisplayName("...")

    @Test(
            groups = "lesson06",
            description = "Verifies that factorial of 5 equals 120 — visible in TestNG reports"
    )
    public void testFactorialWithDescription() {
        Assert.assertEquals(calc.factorial(5), 120);
    }

    // ─────────── INVOCATION COUNT ───────────
    // Run the same test N times. Useful for flaky test detection.
    // JUnit 5 equivalent: @RepeatedTest(5)

    @Test(invocationCount = 5, groups = "lesson06",
            description = "Runs 5 times to verify consistency")
    public void testRepeatedExecution() {
        Assert.assertEquals(calc.add(2, 2), 4);
    }

    // ─────────── INVOCATION COUNT + THREAD POOL ───────────
    // Run N invocations across M threads simultaneously.
    // JUnit 4/5: no built-in equivalent.

    @Test(invocationCount = 10, threadPoolSize = 3, groups = "lesson06",
            description = "Runs 10 times across 3 threads in parallel")
    public void testParallelInvocations() {
        System.out.println("  Thread: " + Thread.currentThread().getName());
        Assert.assertEquals(calc.multiply(2, 3), 6);
    }
}

