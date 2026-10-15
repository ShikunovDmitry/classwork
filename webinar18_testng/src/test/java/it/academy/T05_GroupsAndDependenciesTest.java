package it.academy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 4: Test Groups & Dependencies                                     ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  GROUPS — Categorize tests into named groups.                              ║
 * ║  Run only specific groups via testng.xml or Maven.                         ║
 * ║                                                                            ║
 * ║  DEPENDENCIES — A test can depend on another test or group.                ║
 * ║  If the dependency fails, the dependent test is SKIPPED (not failed).      ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────┬────────────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4      │ JUnit 5                           │      ║
 * ║  ├──────────────┼──────────────┼────────────────────────────────────┤      ║
 * ║  │ groups={}    │ @Category    │ @Tag("name")                      │      ║
 * ║  │              │              │                                    │      ║
 * ║  │ dependsOn-   │ — (none)     │ — (none, but @Order exists for    │      ║
 * ║  │ Methods      │              │   execution order; not true deps) │      ║
 * ║  │ dependsOn-   │              │                                    │      ║
 * ║  │ Groups       │              │                                    │      ║
 * ║  │              │              │                                    │      ║
 * ║  │ ⚠ TestNG deps│              │ ⚠ JUnit 5 has NO dependency      │      ║
 * ║  │ are POWERFUL:│              │ mechanism. If test A fails, test  │      ║
 * ║  │ if dep fails,│              │ B still runs. TestNG SKIPS        │      ║
 * ║  │ test is      │              │ dependent tests automatically.    │      ║
 * ║  │ SKIPPED      │              │                                    │      ║
 * ║  └──────────────┴──────────────┴────────────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T05_GroupsAndDependenciesTest {

    private final Calculator calc = new Calculator();

    // ─────────── TEST GROUPS ───────────
    // A test can belong to one or more groups.
    // Groups can be included/excluded in testng.xml.

    @Test(groups = {"lesson04", "smoke"})
    public void smokeTestAdd() {
        // "smoke" — quick critical-path test
        Assert.assertEquals(calc.add(1, 1), 2);
    }

    @Test(groups = {"lesson04", "smoke"})
    public void smokeTestSubtract() {
        Assert.assertEquals(calc.subtract(5, 3), 2);
    }

    @Test(groups = {"lesson04", "regression"})
    public void regressionTestAddNegatives() {
        // "regression" — more thorough test
        Assert.assertEquals(calc.add(-5, -10), -15);
    }

    @Test(groups = {"lesson04", "regression"})
    public void regressionTestMultiplyByZero() {
        Assert.assertEquals(calc.multiply(100, 0), 0);
    }

    // ─────────── DEPENDENCIES ON METHODS ───────────
    // If smokeTestAdd fails, dependentOnSmoke is SKIPPED (not run).

    @Test(groups = "lesson04", dependsOnMethods = {"smokeTestAdd"})
    public void dependentOnSmoke() {
        // This only runs if smokeTestAdd passed
        Assert.assertEquals(calc.add(10, 20), 30);
        System.out.println("dependentOnSmoke: ran because smokeTestAdd passed ✓");
    }

    // ─────────── DEPENDENCIES ON GROUPS ───────────
    // This test depends on ALL tests in the "smoke" group passing.

    @Test(groups = "lesson04", dependsOnGroups = {"smoke"})
    public void dependentOnSmokeGroup() {
        // Only runs if ALL "smoke" group tests passed
        Assert.assertEquals(calc.multiply(3, 3), 9);
        System.out.println("dependentOnSmokeGroup: ran because all smoke tests passed ✓");
    }

    //even if smokeTestAdd is failed, this test wil be executed after fail
    @Test(groups = "lesson04", dependsOnMethods = {"smokeTestAdd"},alwaysRun = true)
    public void dependentOnSmokeGroupSoftly() {
        // Only runs if ALL "smoke" group tests passed
        Assert.assertEquals(calc.multiply(3, 3), 9);
        System.out.println("dependentOnSmokeGroup: ran because all smoke tests passed ✓");
    }
}

