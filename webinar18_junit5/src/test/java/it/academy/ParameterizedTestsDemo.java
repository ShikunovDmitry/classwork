package it.academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 3: PARAMETERIZED TESTS
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="ParameterizedTestsDemo"
 *
 * ★ JUNIT 5 ONLY — @ParameterizedTest with rich argument sources
 *
 * JUnit 4 had @Parameterized but it was cumbersome (constructor-based, static method).
 * TestNG has @DataProvider — similar concept but JUnit 5's approach is more flexible.
 *
 * JUnit 5 provides MANY argument sources (all ★ JUnit 5 ONLY):
 *   - @ValueSource       — inline primitives/strings
 *   - @NullSource        — provides null
 *   - @EmptySource       — provides empty string/collection
 *   - @NullAndEmptySource — combines both
 *   - @EnumSource        — enum values
 *   - @CsvSource         — inline CSV data
 *   - @CsvFileSource     — CSV data from file
 *   - @MethodSource      — factory method returning Stream/Collection
 */
@DisplayName("Demo 3 — Parameterized Tests ★")
class ParameterizedTestsDemo {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ─── @ValueSource ──────────────────────────────────────────────────────────

    /**
     * ★ @ValueSource — provides a single argument per invocation.
     * Supports: short, byte, int, long, float, double, char, boolean, String, Class.
     */
    @ParameterizedTest(name = "{0} is positive")
    @ValueSource(ints = {1, 5, 100, Integer.MAX_VALUE})
    @DisplayName("★ @ValueSource — positive numbers")
    void testPositiveNumbers(int number) {
        assertTrue(calculator.isPositive(number));
    }

    @ParameterizedTest(name = "\"{0}\" is not blank")
    @ValueSource(strings = {"Hello", "JUnit 5", "  spaces  "})
    @DisplayName("★ @ValueSource with strings")
    void testNonBlankStrings(String input) {
        assertDoesNotThrow(() -> calculator.greet(input));
    }

    // ─── @NullSource, @EmptySource, @NullAndEmptySource ────────────────────────

    /**
     * ★ @NullAndEmptySource = @NullSource + @EmptySource
     * Automatically provides null and empty ("") values.
     * NOT available in JUnit 4 or TestNG.
     */
    @ParameterizedTest(name = "greet(\"{0}\") should throw")
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    @DisplayName("★ @NullAndEmptySource — testing edge cases")
    void testInvalidNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> calculator.greet(name));
    }

    // ─── @EnumSource ───────────────────────────────────────────────────────────

    enum Operation { ADD, SUBTRACT, MULTIPLY, DIVIDE }

    /**
     * ★ @EnumSource — iterates over enum constants.
     * Can filter with 'names' and 'mode' parameters.
     */
    @ParameterizedTest(name = "Operation: {0}")
    @EnumSource(Operation.class)
    @DisplayName("★ @EnumSource — iterate over enum values")
    void testAllOperations(Operation op) {
        assertNotNull(op);
    }

    @ParameterizedTest(name = "Only: {0}")
    @EnumSource(value = Operation.class, names = {"ADD", "SUBTRACT"})
    @DisplayName("★ @EnumSource with filter — only ADD and SUBTRACT")
    void testSelectedOperations(Operation op) {
        assertTrue(op == Operation.ADD || op == Operation.SUBTRACT);
    }

    // ─── @CsvSource ────────────────────────────────────────────────────────────

    /**
     * ★ @CsvSource — inline CSV data, each string = one test invocation.
     * Arguments are automatically converted to the parameter types.
     */
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, -5, 5",
            "0, 0, 0",
            "-1, -1, -2"
    })
    @DisplayName("★ @CsvSource — addition test with CSV data")
    void testAdditionWithCsv(int a, int b, int expectedSum) {
        assertEquals(expectedSum, calculator.add(a, b));
    }

    /**
     * ★ @CsvSource with custom delimiter and quote character.
     */
    @ParameterizedTest(name = "greet(\"{0}\") = \"{1}\"")
    @CsvSource(value = {
            "Alice | Hello, Alice!",
            "Bob   | Hello, Bob!"
    }, delimiter = '|')
    @DisplayName("★ @CsvSource with custom delimiter")
    void testGreetWithCsvDelimiter(String name, String expected) {
        assertEquals(expected.trim(), calculator.greet(name.trim()));
    }

    // ─── @MethodSource ─────────────────────────────────────────────────────────

    /**
     * ★ @MethodSource — references a static factory method that returns
     * a Stream, Collection, Iterable, Iterator, or array of Arguments.
     *
     * Similar to TestNG's @DataProvider but more type-safe.
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("multiplicationData")
    @DisplayName("★ @MethodSource — multiplication test")
    void testMultiplication(int a, int b, int expectedProduct) {
        assertEquals(expectedProduct, calculator.multiply(a, b));
    }

    /** Factory method for @MethodSource. Must be static and return Stream<Arguments>. */
    static Stream<Arguments> multiplicationData() {
        return Stream.of(
                Arguments.of(2, 3, 6),
                Arguments.of(0, 100, 0),
                Arguments.of(-2, 3, -6),
                Arguments.of(7, 7, 49)
        );
    }
}

