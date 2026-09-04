package it.academy;

import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 8: @Factory — Dynamic Test Instance Creation                      ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  @Factory creates multiple instances of a test class with different        ║
 * ║  constructor parameters. Each instance runs all @Test methods.             ║
 * ║                                                                            ║
 * ║  Use cases:                                                                ║
 * ║  • Testing with different configurations                                   ║
 * ║  • Creating test instances dynamically based on runtime data               ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ @Factory     │ @Parameterized   │ @ParameterizedTest            │      ║
 * ║  │              │ (somewhat        │ (somewhat similar,            │      ║
 * ║  │              │  similar)        │  but per-method)              │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ ⚠ @Factory   │                  │ ⚠ No direct equivalent in    │      ║
 * ║  │ creates      │                  │ JUnit 5. Factory creates      │      ║
 * ║  │ multiple     │                  │ multiple CLASS INSTANCES,     │      ║
 * ║  │ test class   │                  │ not just method invocations.  │      ║
 * ║  │ instances!   │                  │                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T09_FactoryTest {

    private final int a;
    private final int b;
    private final int expectedSum;

    // Constructor receives parameters from @Factory
    public T09_FactoryTest(int a, int b, int expectedSum) {
        this.a = a;
        this.b = b;
        this.expectedSum = expectedSum;
    }

    // @Factory method creates test instances — each instance runs all @Test methods
    @Factory
    public static Object[] createInstances() {
        return new Object[]{
                new T09_FactoryTest(1, 2, 3),
                new T09_FactoryTest(10, 20, 30),
                new T09_FactoryTest(-5, 5, 0),
                new T09_FactoryTest(0, 0, 0),
                new T09_FactoryTest(100, -100, 0)
        };
    }

    @Test(groups = "lesson08")
    public void testAddition() {
        Calculator calc = new Calculator();
        System.out.println(String.format("  Factory instance: add(%d, %d) = %d", a, b, expectedSum));
        Assert.assertEquals(calc.add(a, b), expectedSum);
    }

    @Test(groups = "lesson08")
    public void testCommutative() {
        Calculator calc = new Calculator();
        // a + b should equal b + a
        Assert.assertEquals(calc.add(a, b), calc.add(b, a),
                "Addition should be commutative");
    }
}

