package it.academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 4: NESTED TESTS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="NestedTestsDemo"
 *
 * ★ JUNIT 5 ONLY — @Nested
 *
 * Allows grouping related tests into inner classes, creating a hierarchical
 * test structure. Each nested class can have its own @BeforeEach / @AfterEach.
 *
 * NOT available in JUnit 4 (it had @RunWith(Enclosed.class) but very limited).
 * NOT available in TestNG.
 *
 * Benefits:
 *   - Better test organization and readability
 *   - Shared setup within a group
 *   - Hierarchical display in test reports
 */
@DisplayName("Demo 4 — ★ Nested Tests (JUnit 5 ONLY)")
class NestedTestsDemo {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Addition tests")
    class AdditionTests {

        @Test
        @DisplayName("Adding two positive numbers")
        void addPositives() {
            assertEquals(5, calculator.add(2, 3));
        }

        @Test
        @DisplayName("Adding two negative numbers")
        void addNegatives() {
            assertEquals(-5, calculator.add(-2, -3));
        }

        @Test
        @DisplayName("Adding zero")
        void addZero() {
            assertEquals(3, calculator.add(3, 0));
        }
    }

    @Nested
    @DisplayName("Division tests")
    class DivisionTests {

        @Test
        @DisplayName("Normal division")
        void normalDivision() {
            assertEquals(5, calculator.divide(10, 2));
        }

        @Nested
        @DisplayName("Edge cases")
        class EdgeCases {

            @Test
            @DisplayName("Division by zero throws ArithmeticException")
            void divisionByZero() {
                assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
            }

            @Test
            @DisplayName("Zero divided by any number is zero")
            void zeroDivided() {
                assertEquals(0, calculator.divide(0, 5));
            }
        }
    }

    @Nested
    @DisplayName("Greet method tests")
    class GreetTests {

        @Test
        @DisplayName("Valid name returns greeting")
        void validName() {
            assertEquals("Hello, World!", calculator.greet("World"));
        }

        @Test
        @DisplayName("Null name throws exception")
        void nullName() {
            assertThrows(IllegalArgumentException.class, () -> calculator.greet(null));
        }

        @Test
        @DisplayName("Blank name throws exception")
        void blankName() {
            assertThrows(IllegalArgumentException.class, () -> calculator.greet("   "));
        }
    }

}

