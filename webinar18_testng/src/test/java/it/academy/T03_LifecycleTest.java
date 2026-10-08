package it.academy;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 2: Test Lifecycle — Setup & Teardown Annotations                  ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG lifecycle (execution order):                                       ║
 * ║                                                                            ║
 * ║    @BeforeSuite      → once before ALL tests in the suite                  ║
 * ║      @BeforeTest     → once before each <test> tag in testng.xml           ║
 * ║        @BeforeClass  → once before the first method in this class          ║
 * ║          @BeforeMethod → before EACH @Test method                          ║
 * ║            @Test                                                           ║
 * ║          @AfterMethod  → after EACH @Test method                           ║
 * ║        @AfterClass   → once after the last method in this class            ║
 * ║      @AfterTest      → once after each <test> tag                          ║
 * ║    @AfterSuite       → once after ALL tests in the suite                   ║
 * ║                                                                            ║
 * ║  ┌─────────────────────────────────────────────────────────────────────┐    ║
 * ║  │  COMPARISON                                                        │    ║
 * ║  ├─────────────────┬─────────────────┬───────────────────────────────┤    ║
 * ║  │  TestNG          │  JUnit 4        │  JUnit 5                     │    ║
 * ║  ├─────────────────┼─────────────────┼───────────────────────────────┤    ║
 * ║  │ @BeforeSuite     │  — (none)       │  — (none)                    │    ║
 * ║  │ @BeforeTest      │  — (none)       │  — (none)                    │    ║
 * ║  │ @BeforeClass     │ @BeforeClass    │ @BeforeAll                   │    ║
 * ║  │ @BeforeMethod    │ @Before         │ @BeforeEach                  │    ║
 * ║  │ @AfterMethod     │ @After          │ @AfterEach                   │    ║
 * ║  │ @AfterClass      │ @AfterClass     │ @AfterAll                    │    ║
 * ║  │ @AfterTest       │  — (none)       │  — (none)                    │    ║
 * ║  │ @AfterSuite      │  — (none)       │  — (none)                    │    ║
 * ║  │                  │                 │                               │    ║
 * ║  │ ⚠ TestNG does    │ @BeforeClass    │ @BeforeAll must be static    │    ║
 * ║  │ NOT require      │ must be static  │ (unless using per-class      │    ║
 * ║  │ static methods!  │                 │  lifecycle)                   │    ║
 * ║  └─────────────────┴─────────────────┴───────────────────────────────┘    ║
 * ║                                                                            ║
 * ║  KEY DIFFERENCE: TestNG has @BeforeSuite/@AfterSuite and                   ║
 * ║  @BeforeTest/@AfterTest — these have NO equivalent in JUnit 4 or 5!        ║
 * ║  They allow cross-class setup/teardown at the suite or <test> level.       ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T03_LifecycleTest {

    private Calculator calc;

    // ── Runs once before ALL tests in the entire suite ──
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("══════ @BeforeSuite: global setup (DB connections, server start, etc.) ══════");
    }

    // ── Runs once before this <test> tag in testng.xml ──
    @BeforeTest
    public void beforeTest() {
        System.out.println("  ════ @BeforeTest: test-level setup ════");
    }

    // ── Runs once before the first @Test method in this class ──
    // JUnit 4: @BeforeClass (must be static!)
    // JUnit 5: @BeforeAll  (must be static!)
    // TestNG: does NOT need to be static!
    @BeforeClass
    public void beforeClass() {
        System.out.println("    ══ @BeforeClass: class-level setup ══");
        calc = new Calculator();
    }

    // ── Runs before EACH @Test method ──
    // JUnit 4: @Before
    // JUnit 5: @BeforeEach
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("      ─ @BeforeMethod: per-test setup ─");
    }

    @Test(groups = "lesson02")
    public void testAdd() {
        System.out.println("        ▶ testAdd running");
        Assert.assertEquals(calc.add(1, 1), 2);
    }

    @Test(groups = "lesson02")
    public void testSubtract() {
        System.out.println("        ▶ testSubtract running");
        Assert.assertEquals(calc.subtract(5, 3), 2);
    }

    // ── Runs after EACH @Test method ──
    // JUnit 4: @After
    // JUnit 5: @AfterEach
    @AfterMethod
    public void afterMethod() {
        System.out.println("      ─ @AfterMethod: per-test cleanup ─");
    }

    // ── Runs once after the last @Test method in this class ──
    @AfterClass
    public void afterClass() {
        System.out.println("    ══ @AfterClass: class-level cleanup ══");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("  ════ @AfterTest: test-level cleanup ════");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("══════ @AfterSuite: global cleanup ══════");
    }
}

