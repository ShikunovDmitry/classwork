package it.academy;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.*;
import static org.junit.Assume.assumeTrue;

public class AssumptionsTest {
  @Test
  public void testOnlyOnMac() {
    // This test will only run on macOS; on other OSes it will be skipped
    assumeTrue("Test requires macOS",
        System.getProperty("os.name").toLowerCase().contains("mac"));

    // If we reach here, we're on macOS
    assertNotNull(System.getProperty("user.home"));
  }

  @Test
  public void testOnlyOnWindows() {
    // This test will be SKIPPED on macOS/Linux since the assumption fails
    assumeTrue("Test requires Windows",
        System.getProperty("os.name").toLowerCase().contains("windows"));

    assertTrue("Should only run on Windows", true);
  }

  @Test
  public void testAssumeNotNull() {
    String envVar = System.getenv("DEMO_VARIABLE"); // likely null
    // If DEMO_VARIABLE is not set, this test will be skipped (not failed)
    assumeNotNull(envVar);

    assertFalse("DEMO_VARIABLE should not be empty", envVar.isEmpty());
  }

  @Test
  public void testAssumeFalse() {
    // Skip this test on CI environments
    assumeFalse("Skipping on CI",
        "true".equals(System.getenv("CI")));

    // Local-only test logic
    Calculator calc = new Calculator();
    assertEquals(15, calc.add(7, 8));
  }

  @Test
  public void testAssumptionPasses_thenTestRuns() {
    // This assumption will always pass
    assumeTrue("Java version should not be null",
        System.getProperty("java.version") != null);

    Calculator calc = new Calculator();
    assertEquals(6, calc.multiply(2, 3));
  }
}
