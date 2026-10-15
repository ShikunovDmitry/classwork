package it.academy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 15: EXCEPTION HANDLING PATTERNS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="ExceptionHandlingDemo"
 *
 * Comparison of exception testing approaches:
 *
 * ┌──────────────────────────────────────────────────────────────────────────────┐
 * │ Framework  │ Approach                     │ Can inspect message/cause?      │
 * ├──────────────────────────────────────────────────────────────────────────────┤
 * │ JUnit 4    │ @Test(expected=X.class)       │ ❌ No                          │
 * │ JUnit 4    │ @Rule ExpectedException       │ ✅ Yes, but verbose            │
 * │ TestNG     │ @Test(expectedExceptions=...) │ ❌ No (only type)              │
 * │ JUnit 5 ★  │ assertThrows() + lambda       │ ✅ Yes — clean and flexible    │
 * └──────────────────────────────────────────────────────────────────────────────┘
 */
@DisplayName("Demo 15 — Exception Handling Patterns")
class ExceptionHandlingDemo {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("★ assertThrows — catch and inspect exception")
    void assertThrowsWithInspection() {
        // Catch the exception
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.greet(null)
        );

        // Inspect the exception in detail
        assertAll("Exception details",
                () -> assertNotNull(ex),
                () -> assertTrue(ex.getMessage().contains("null or blank")),
                () -> assertNull(ex.getCause())  // No cause in this case
        );
    }

    @Test
    @DisplayName("★ assertThrows — verify exception type hierarchy")
    void exceptionTypeHierarchy() {
        // ArithmeticException IS-A RuntimeException
        assertThrows(RuntimeException.class, () -> calculator.divide(1, 0));
        // More specific type also works
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }

    @Test
    @DisplayName("★ assertDoesNotThrow — explicit no-exception assertion")
    void noExceptionAssertion() {
        // ★ JUNIT 5 ONLY — explicitly assert no exception is thrown
        String result = assertDoesNotThrow(() -> calculator.greet("Alice"));
        assertEquals("Hello, Alice!", result);
    }

    @Test
    @DisplayName("★ assertThrows — wrong exception type fails the test")
    void wrongExceptionType() {
        // This would FAIL if divide(1,0) threw something other than ArithmeticException
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }
}

