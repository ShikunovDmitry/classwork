package it.academy;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Demonstrates using @Listeners annotation to attach the custom listener.
 * The listener can also be configured globally in testng.xml.
 *
 * Ways to register a listener:
 * 1. @Listeners annotation on the test class (shown here)
 * 2. <listeners> tag in testng.xml
 * 3. ServiceLoader SPI (META-INF/services)
 */
@Listeners(CustomTestListener.class)
public class T10_ListenerTest {

    @Test(groups = "lesson09")
    public void testThatPasses() {
        System.out.println("    Executing test that will pass");
        Assert.assertTrue(true);
    }

    @Test(groups = "lesson09")
    public void testThatAlsoPasses() {
        System.out.println("    Executing another passing test");
        Assert.assertEquals(1 + 1, 2);
    }

    // ─────────── RETRY ANALYZER ───────────
    // If this test fails, TestNG will retry it up to 2 more times.
    // Unique to TestNG — no built-in equivalent in JUnit.

    @Test(groups = "lesson09", retryAnalyzer = RetryAnalyzer.class)
    public void testWithRetry() {
        System.out.println("    Executing test with retry support");
        // This passes, but if it used flaky logic, the RetryAnalyzer would retry it.
        Assert.assertTrue(true);
    }
}

