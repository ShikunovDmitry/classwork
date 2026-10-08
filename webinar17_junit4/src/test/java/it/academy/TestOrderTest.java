package it.academy;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrderTest {
  private static int step = 0;
  @Test
  public void test3_third() {
    step++;
    System.out.println("Step " + step + ": test3_third");
    assertEquals("Should be the third step", 3, step);
  }

  @Test
  public void test1_first() {
    step++;
    System.out.println("Step " + step + ": test1_first");
    assertEquals("Should be the first step", 1, step);
  }

  @Test
  public void test2_second() {
    step++;
    System.out.println("Step " + step + ": test2_second");
    assertEquals("Should be the second step", 2, step);
  }
}
