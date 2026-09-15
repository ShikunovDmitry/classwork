package it.academy;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicAssertionTest {

  private final Calculator calc = new Calculator();

  // ----- assertEquals / assertNotEquals -----

  @Test
  public void testAssertEquals_integers() {
    assertEquals("2 + 3 should equal 5", 5, calc.add(2, 3));
  }

  @Test
  public void testAssertEquals_strings() {
    assertEquals("Hello World", calc.concatenate("Hello", "World"));
  }

  @Test
  public void testAssertEquals_doubles_withDelta() {
    assertEquals("PI approximation", 3.14, 3.14159, 0.01);
  }

  @Test
  public void testAssertNotEquals() {
    assertNotEquals("2 + 3 should not equal 6", 6, calc.add(2, 3));
  }

  // ----- assertTrue / assertFalse -----

  @Test
  public void testAssertTrue() {
    assertTrue("4 should be even", calc.isEven(4));
  }

  @Test
  public void testAssertFalse() {
    assertFalse("7 should be odd", calc.isEven(7));
  }

  // ----- assertNull / assertNotNull -----

  @Test
  public void testAssertNull() {
    Object obj = null;
    assertNull("Object should be null", obj);
  }

  @Test
  public void testAssertNotNull() {
    Object obj = new Calculator();
    assertNotNull("Object should not be null", obj);
  }

  // ----- assertSame / assertNotSame -----

  @Test
  public void testAssertSame() {
    String a = "hello";
    String b = a;
    assertSame("Should be the exact same object", a, b);
  }

  @Test
  public void testAssertNotSame() {
    String a = new String("hello");
    String b = new String("hello");
    assertNotSame("Should be different object instances", a, b);
  }

  // ----- assertArrayEquals -----

  @Test
  public void testAssertArrayEquals() {
    int[] expected = {1, 2, 3};
    int[] actual = {1, 2, 3};
    assertArrayEquals("Arrays should be equal element-by-element", expected, actual);
  }

  // ----- fail() -----

  @Test
  public void testFailExample() {
    try {
      calc.divide(10, 0);
      fail("Expected ArithmeticException was not thrown");
    } catch (ArithmeticException e) {
      assertEquals("Cannot divide by zero", e.getMessage());
    }
  }
}
