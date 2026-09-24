package it.academy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 10: Parallel Test Execution                                       ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG supports parallel execution at multiple levels (via testng.xml):   ║
 * ║  • parallel="methods"  — methods within a class run in parallel            ║
 * ║  • parallel="classes"  — different classes run in parallel                 ║
 * ║  • parallel="tests"    — different <test> tags run in parallel             ║
 * ║  • parallel="instances"— different instances of same class in parallel     ║
 * ║                                                                            ║
 * ║  thread-count="N" controls maximum parallel threads.                       ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ parallel=    │ — (none built-in,│ junit.jupiter.execution       │      ║
 * ║  │ "methods"    │  need maven-     │  .parallel.enabled = true     │      ║
 * ║  │ parallel=    │  surefire fork   │                               │      ║
 * ║  │ "classes"    │  config)         │ junit.jupiter.execution       │      ║
 * ║  │ parallel=    │                  │  .parallel.mode.default =     │      ║
 * ║  │ "tests"      │                  │  concurrent                   │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ ⚠ TestNG     │                  │ ⚠ TestNG parallel config is  │      ║
 * ║  │ parallel is  │                  │ in testng.xml (declarative).  │      ║
 * ║  │ much more    │                  │ JUnit 5 uses properties file. │      ║
 * ║  │ granular!    │                  │                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * To enable parallel execution, configure testng.xml:
 * <suite name="..." parallel="methods" thread-count="4">
 *
 * See: src/test/resources/testng-parallel.xml
 */
public class T11_ParallelTest {

    private final Calculator calc = new Calculator();

    @Test(groups = "lesson10")
    public void parallelTest1() {
        System.out.println("  parallelTest1 on thread: " + Thread.currentThread().getName());
        Assert.assertEquals(calc.add(1, 1), 2);
        sleep(500);
    }

    @Test(groups = "lesson10")
    public void parallelTest2() {
        System.out.println("  parallelTest2 on thread: " + Thread.currentThread().getName());
        Assert.assertEquals(calc.multiply(3, 3), 9);
        sleep(500);
    }

    @Test(groups = "lesson10")
    public void parallelTest3() {
        System.out.println("  parallelTest3 on thread: " + Thread.currentThread().getName());
        Assert.assertTrue(calc.isEven(4));
        sleep(500);
    }

    @Test(groups = "lesson10")
    public void parallelTest4() {
        System.out.println("  parallelTest4 on thread: " + Thread.currentThread().getName());
        Assert.assertFalse(calc.isEven(7));
        sleep(500);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

