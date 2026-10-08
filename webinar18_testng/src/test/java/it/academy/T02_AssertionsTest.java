package it.academy;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 1B: Assertions in Detail                                          ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG provides:                                                          ║
 * ║  • Hard assertions (Assert) — fail immediately on first failure            ║
 * ║  • Soft assertions (SoftAssert) — collect all failures, report at end      ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────┬────────────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4      │ JUnit 5                           │      ║
 * ║  ├──────────────┼──────────────┼────────────────────────────────────┤      ║
 * ║  │ SoftAssert   │ ErrorCollector│ assertAll(() -> ..., () -> ...)  │      ║
 * ║  │ (built-in)   │ (@Rule)      │ (built-in)                       │      ║
 * ║  │              │              │                                    │      ║
 * ║  │ assertNotNull│ assertNotNull│ assertNotNull                     │      ║
 * ║  │ assertTrue   │ assertTrue   │ assertTrue                        │      ║
 * ║  │ assertFalse  │ assertFalse  │ assertFalse                       │      ║
 * ║  │ assertNull   │ assertNull   │ assertNull                        │      ║
 * ║  └──────────────┴──────────────┴────────────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T02_AssertionsTest {

    // ─────────── HARD ASSERTIONS ───────────
    // Execution stops at the FIRST failed assertion.

    @Test(groups = "lesson01")
    public void testAssertEquals() {
        Assert.assertEquals("hello", "hello");
        Assert.assertEquals(42, 42);
        Assert.assertEquals(3.14, 3.14, 0.001); // delta for doubles
    }

    @Test(groups = "lesson01", description = "assert not equal stest")
    public void testAssertNotEquals() {
        Assert.assertNotEquals("hello", "world");
    }

    @Test
    public void testAssertTrueFalse() {
        Assert.assertTrue(10 > 5, "10 should be greater than 5");
        Assert.assertFalse(5 > 10, "5 should NOT be greater than 10");
    }

    @Test(groups = "lesson01")
    public void testAssertNullNotNull() {
        Object obj = new Object();
        Assert.assertNotNull(obj, "Object should not be null");

        Object nullObj = null;
        Assert.assertNull(nullObj, "Object should be null");
    }

    @Test(groups = "lesson01")
    public void testAssertSameNotSame() {
        // assertSame checks reference equality (==), not .equals()
        String a = "hello";
        String b = a;
        Assert.assertSame(a, b, "Should be the same reference");

        String c = new String("hello");
        Assert.assertNotSame(a, c, "Should be different references");
    }

    // ─────────── SOFT ASSERTIONS ───────────
    // All assertions run; failures are collected and reported at the end.
    // ⚠ You MUST call softAssert.assertAll() at the end!
    //
    // JUnit 4 equivalent: @Rule ErrorCollector
    // JUnit 5 equivalent: Assertions.assertAll(() -> ..., () -> ...)

    @Test(groups = "lesson01")
    public void testSoftAssertions() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(1 + 1, 2, "1+1 should be 2");
        softAssert.assertEquals(2 * 2, 4, "2*2 should be 4");
        softAssert.assertTrue("TestNG".startsWith("Test"), "Should start with 'Test'");
        softAssert.assertNotNull("value", "Should not be null");

        // ⚠ IMPORTANT: without assertAll(), failures are silently swallowed!
        softAssert.assertAll();
    }
}

