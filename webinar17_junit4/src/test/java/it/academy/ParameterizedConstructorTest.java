package it.academy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedConstructorTest {
  private final int input;
  private final long expectedFactorial;

  /**
   * Constructor receives one row of test data.
   */
  public ParameterizedConstructorTest(int input, long expectedFactorial) {
    this.input = input;
    this.expectedFactorial = expectedFactorial;
  }

  @Parameterized.Parameters(name = "{index}: factorial({0}) = {1}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {0, 1L},
        {1, 1L},
        {2, 2L},
        {3, 6L},
        {5, 120L},
        {10, 3_628_800L},
        {12, 479_001_600L}
    });
  }

  @Test
  public void testFactorial() {
    Calculator calc = new Calculator();
    assertEquals(expectedFactorial, calc.factorial(input));
  }
}
