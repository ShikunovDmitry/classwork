package it.academy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ExceptionTestingTest {
  private final Calculator calc = new Calculator();

  // ───── Approach 1: @Test(expected) ─────

  /**
   * The simplest way: just declare the expected exception type in the annotation.
   * The test PASSES if ArithmeticException is thrown, FAILS otherwise.
   */
  @Test(expected = ArithmeticException.class)
  public void testDivideByZero_withExpectedAnnotation() {
    calc.divide(10, 0);
  }

  @Test(expected = NullPointerException.class)
  public void testConcatenateNull_withExpectedAnnotation() {
    calc.concatenate(null, "world");
  }

  // ───── Approach 2: try-catch + fail() ─────

  /**
   * Manual try-catch gives full control: you can assert on the message,
   * the cause, or any other property of the exception.
   */
  @Test
  public void testDivideByZero_withTryCatch() {
    try {
      calc.divide(10, 0);
      fail("Expected ArithmeticException but none was thrown");
    } catch (ArithmeticException e) {
      assertEquals("Cannot divide by zero", e.getMessage());
    }
  }

  // ───── Approach 3: ExpectedException Rule ─────

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * ExpectedException lets you set the expectation BEFORE the throwing call.
   * You can verify the exception class AND its message in one go.
   */
  @Test
  public void testNegativeFactorial_withExpectedExceptionRule() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Negative numbers are not allowed");

    calc.factorial(-5);
  }

  // ───── Approach 4: assertThrows (JUnit 4.13+) ─────

  /**
   * assertThrows runs the executable, catches the exception, and returns it
   * so you can perform further assertions.
   */
  @Test
  public void testDivideByZero_withAssertThrows() {
    ArithmeticException ex = assertThrows(
        ArithmeticException.class,
        () -> calc.divide(10, 0)
    );
    assertEquals("Cannot divide by zero", ex.getMessage());
  }

  @Test
  public void testNegativeFactorial_withAssertThrows() {
    IllegalArgumentException ex = assertThrows(
        IllegalArgumentException.class,
        () -> calc.factorial(-1)
    );
    assertTrue(ex.getMessage().contains("Negative"));
  }
}
