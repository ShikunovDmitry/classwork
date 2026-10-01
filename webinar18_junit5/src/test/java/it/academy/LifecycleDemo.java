package it.academy;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 1: BASIC ANNOTATIONS & LIFECYCLE
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="LifecycleDemo"
 *
 * JUnit 5 annotations vs JUnit 4 vs TestNG:
 * ┌──────────────────────┬──────────────────┬──────────────────┬──────────────────┐
 * │ JUnit 5              │ JUnit 4          │ TestNG           │ Notes            │
 * ├──────────────────────┼──────────────────┼──────────────────┼──────────────────┤
 * │ @Test                │ @Test            │ @Test            │ same concept     │
 * │ @BeforeEach          │ @Before          │ @BeforeMethod    │ renamed          │
 * │ @AfterEach           │ @After           │ @AfterMethod     │ renamed          │
 * │ @BeforeAll           │ @BeforeClass     │ @BeforeClass     │ renamed          │
 * │ @AfterAll            │ @AfterClass      │ @AfterClass      │ renamed          │
 * │ @Disabled            │ @Ignore          │ @Test(enabled)   │ renamed          │
 * │ @DisplayName ★       │ —                │ —                │ JUnit 5 ONLY     │
 * │ @Nested ★            │ —                │ —                │ JUnit 5 ONLY     │
 * │ @Tag                 │ @Category        │ @Test(groups)    │ renamed          │
 * └──────────────────────┴──────────────────┴──────────────────┴──────────────────┘
 *
 * ★ = Feature unique to JUnit 5, not available in JUnit 4 or TestNG.
 */
class LifecycleDemo {

    private Calculator calculator;

    // ─── Lifecycle callbacks ───────────────────────────────────────────────────

    /**
     * Runs ONCE before all tests in this class.
     * Must be static (unless @TestInstance(PER_CLASS) is used — see Demo 6).
     *
     * JUnit 4 equivalent: @BeforeClass
     * TestNG equivalent:  @BeforeClass
     */
    @BeforeAll
    static void setUpAll() {
        System.out.println(">>> @BeforeAll — runs once before ALL tests");
    }

    /**
     * Runs ONCE after all tests in this class.
     *
     * JUnit 4 equivalent: @AfterClass
     * TestNG equivalent:  @AfterClass
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println(">>> @AfterAll — runs once after ALL tests");
    }

    /**
     * Runs before EACH test method.
     *
     * JUnit 4 equivalent: @Before
     * TestNG equivalent:  @BeforeMethod
     */
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("  > @BeforeEach — fresh App instance created");
    }

    /**
     * Runs after EACH test method.
     *
     * JUnit 4 equivalent: @After
     * TestNG equivalent:  @AfterMethod
     */
    @AfterEach
    void tearDown() {
        System.out.println("  > @AfterEach — cleanup after test");
    }

    // ─── Basic test ────────────────────────────────────────────────────────────

    @Test
    void basicAdditionTest() {
        assertEquals(5, calculator.add(2, 3));
    }

    // ─── @DisplayName ──────────────────────────────────────────────────────────

    /**
     * ★ JUNIT 5 ONLY — @DisplayName
     * Provides a human-readable name for the test in reports.
     * NOT available in JUnit 4 or TestNG.
     */
    @Test
    @DisplayName("2 + 3 should equal 5 ✅")
    void additionWithDisplayName() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 must be 5");
    }

    // ─── @Disabled ─────────────────────────────────────────────────────────────

    /**
     * @Disabled replaces JUnit 4's @Ignore.
     * TestNG uses @Test(enabled = false).
     */
    @Test
    @Disabled("Demonstrating @Disabled — this test is intentionally skipped")
    void skippedTest() {
        fail("This should never run");
    }

    // ─── @Tag ──────────────────────────────────────────────────────────────────

    /**
     * @Tag replaces JUnit 4's @Category and TestNG's groups.
     * Used for filtering tests at build-time (e.g., mvn -Dgroups="fast").
     */
    @Test
    @Tag("fast")
    @DisplayName("Subtraction test tagged as 'fast'")
    void subtractionTagged() {
        assertEquals(2, calculator.subtract(5, 3));
    }
}

