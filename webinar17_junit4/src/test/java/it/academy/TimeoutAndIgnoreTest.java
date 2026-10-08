package it.academy;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TimeoutAndIgnoreTest {
  private final Calculator calc = new Calculator();

  // ───── Timeout ─────

  /**
   * This test must complete within 100 ms.
   * calc.add is fast, so it will easily pass.
   */
  @Test(timeout = 100)
  public void testAdd_withinTimeout() {
    assertEquals(10, calc.add(4, 6));
  }

  /**
   * This test has a 500ms timeout but the slowOperation() sleeps for 3 seconds.
   * The test framework will interrupt the thread and fail the test.
   *
   * UNCOMMENT to see it in action (it will fail as expected):
   */
   @Test(timeout = 500)
   public void testSlowOperation_exceedsTimeout() {
       calc.slowOperation(); // takes ~3000ms → will be interrupted
   }

  // ───── @Ignore ─────

  /**
   * This test is skipped by the runner with the reason provided.
   * It will appear in the report as "ignored" (yellow in most IDEs).
   */
  @Ignore("Demonstrating @Ignore – this test is intentionally skipped")
  @Test
  public void testIgnoredExample() {
    fail("This line is never reached because the test is ignored");
  }

  /**
   * A normal passing test – included so the class isn't entirely ignored.
   */
  @Test
  public void testSubtract() {
    assertEquals(3, calc.subtract(10, 7));
  }
}
