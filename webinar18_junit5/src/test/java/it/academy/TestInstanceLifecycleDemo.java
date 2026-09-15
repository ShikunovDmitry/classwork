package it.academy;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 6: TEST INSTANCE LIFECYCLE
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="TestInstanceLifecycleDemo"
 *
 * ★ JUNIT 5 ONLY — @TestInstance(Lifecycle.PER_CLASS)
 *
 * By default, JUnit 5 creates a NEW test class instance for EACH test method
 * (same as JUnit 4). This ensures test isolation.
 *
 * With @TestInstance(PER_CLASS):
 *   - ONE instance is shared across ALL test methods
 *   - @BeforeAll / @AfterAll do NOT need to be static
 *   - Tests can share mutable state (use with caution!)
 *
 * NOT available in JUnit 4.
 * TestNG uses PER_CLASS by default (opposite of JUnit).
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // ★ JUnit 5 ONLY
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Demo 6 — ★ @TestInstance PER_CLASS (JUnit 5 ONLY)")
class TestInstanceLifecycleDemo {

    private Calculator calculator;
    private int invocationCount = 0;  // Shared state across tests

    /**
     * With PER_CLASS lifecycle, @BeforeAll does NOT need to be static!
     */
    @BeforeAll
    void setUpAll() {  // Not static!
        calculator = new Calculator();
        System.out.println(">>> @BeforeAll (non-static with PER_CLASS)");
    }

    @Test
    @Order(1)
    @DisplayName("First test increments counter")
    void firstTest() {
        invocationCount++;
        assertEquals(1, invocationCount, "Counter should be 1 after first test");
    }

    @Test
    @Order(2)
    @DisplayName("Second test sees the shared state")
    void secondTest() {
        invocationCount++;
        // State is shared — counter retains value from previous test
        assertEquals(2, invocationCount, "Counter should be 2 — state is shared!");
    }

    @Test
    @Order(3)
    @DisplayName("Third test also sees the shared state")
    void thirdTest() {
        invocationCount++;
        assertEquals(3, invocationCount, "Counter should be 3 — PER_CLASS lifecycle");
    }
}

