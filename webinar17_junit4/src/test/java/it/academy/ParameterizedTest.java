package it.academy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {
  /**
   * @Parameters provides the test data.
   * Each Object[] is: { inputA, inputB, expectedSum }
   * The 'name' attribute makes test reports more readable.
   */
  @Parameterized.Parameters(name = "{index}: add({0}, {1}) = {2}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {0, 0, 0},       // zero + zero
        {1, 1, 2},       // positive + positive
        {-1, -1, -2},    // negative + negative
        {1, -1, 0},      // positive + negative
        {100, 200, 300}, // larger numbers
        {Integer.MAX_VALUE, 0, Integer.MAX_VALUE}  // boundary
    });
  }

  // Fields injected by the Parameterized runner via @Parameter

  @Parameterized.Parameter(0)
  public int inputA;

  @Parameterized.Parameter(1)
  public int inputB;

  @Parameterized.Parameter(2)
  public int expectedSum;

  @Test

  public void testAdd() {
    Calculator calc = new Calculator();
    System.out.println(inputA + " + " + inputB + " = " + expectedSum);
    assertEquals(expectedSum, calc.add(inputA, inputB));
  }
}
