package it.academy;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersTest {
  private final Calculator calc = new Calculator();

  // ───── Basic matchers ─────

  @Test
  public void testIs() {
    assertThat(calc.add(2, 3), is(5));
  }

  @Test
  public void testEqualTo() {
    assertThat(calc.multiply(3, 4), is(equalTo(12)));
  }

  @Test
  public void testNot() {
    assertThat(calc.add(2, 2), is(not(5)));
  }

  // ───── Null checks ─────

  @Test
  public void testNullValue() {
    String value = null;
    assertThat(value, is(nullValue()));
  }

  @Test
  public void testNotNullValue() {
    assertThat(calc.concatenate("a", "b"), is(notNullValue()));
  }

  // ───── Type checks ─────

  @Test
  public void testInstanceOf() {
    Object obj = new Calculator();
    assertThat(obj, is(instanceOf(Calculator.class)));
  }

  // ───── String matchers ─────

  @Test
  public void testContainsString() {
    assertThat(calc.concatenate("Hello", "World"), containsString("lo Wo"));
  }

  @Test
  public void testStartsAndEndsWith() {
    String result = calc.concatenate("Hello", "World");
    assertThat(result, startsWith("Hello"));
    assertThat(result, endsWith("World"));
  }

  // ───── Numeric matchers ─────

  @Test
  public void testGreaterThanAndLessThan() {
    assertThat(calc.add(10, 5), is(greaterThan(10)));
    assertThat(calc.subtract(10, 5), is(lessThan(10)));
  }

  @Test
  public void testGreaterThanOrEqualTo() {
    assertThat(calc.add(5, 5), is(greaterThanOrEqualTo(10)));
  }

  // ───── Collection matchers ─────

  @Test
  public void testHasItemAndHasSize() {
    UserService service = new UserService();
    service.addUser("Alice");
    service.addUser("Bob");

    assertThat(service.getAllUsers(), hasItem("Alice"));
    assertThat(service.getAllUsers(), hasSize(2));
    assertThat(service.getAllUsers(), hasItems("Alice", "Bob"));
  }

  // ───── Combining matchers ─────

  @Test
  public void testAllOf() {
    // allOf = AND: all conditions must hold
    int result = calc.add(3, 7);
    assertThat(result, allOf(greaterThan(5), lessThan(20), is(10)));
  }

  @Test
  public void testAnyOf() {
    // anyOf = OR: at least one condition must hold
    int result = calc.multiply(3, 3);
    assertThat(result, anyOf(is(9), is(10), is(11)));
  }
}
