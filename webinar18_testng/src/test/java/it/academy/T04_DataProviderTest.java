package it.academy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 3: DataProviders & Parameterized Tests                            ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG provides TWO ways to parameterize tests:                           ║
 * ║  1. @DataProvider — data defined in Java code (most common)                ║
 * ║  2. @Parameters   — values supplied from testng.xml                        ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬───────────────────┬──────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4           │ JUnit 5                      │      ║
 * ║  ├──────────────┼───────────────────┼──────────────────────────────┤      ║
 * ║  │ @DataProvider│ @Parameterized    │ @ParameterizedTest +         │      ║
 * ║  │              │ (runs entire      │   @ValueSource               │      ║
 * ║  │              │  class with one   │   @CsvSource                 │      ║
 * ║  │              │  param set)       │   @MethodSource              │      ║
 * ║  │              │                   │   @CsvFileSource             │      ║
 * ║  │              │                   │   @EnumSource                │      ║
 * ║  │              │                   │                              │      ║
 * ║  │ ⚠ TestNG     │ ⚠ JUnit 4        │ JUnit 5 is more flexible    │      ║
 * ║  │ DataProvider │ @Parameterized    │ than JUnit 4 but TestNG's   │      ║
 * ║  │ is per-method│ is per-class      │ DataProvider can return      │      ║
 * ║  │ (more        │ (less flexible)   │ any Object[][] — maximum     │      ║
 * ║  │ flexible)    │                   │ flexibility                  │      ║
 * ║  └──────────────┴───────────────────┴──────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T04_DataProviderTest {

    private final Calculator calc = new Calculator();

    // ─────────── BASIC DATA PROVIDER ───────────
    // A @DataProvider method returns Object[][] where each row is one test invocation.

    @DataProvider(name = "additionData")
    public Object[][] additionDataProvider() {
        return new Object[][]{
                {1, 1, 2},      // a=1, b=1, expected=2
                {2, 3, 5},
                {-1, 1, 0},
                {0, 0, 0},
                {100, 200, 300},
                {-5, -10, -15}
        };
    }

    @Test(dataProvider = "additionData",dataProviderClass = T04_DataProviderTest.class, groups = {"lesson03","smoke"})
    public void testAddWithDataProvider(int a, int b, int expected) {
        Assert.assertEquals(calc.add(a, b), expected,
                String.format("add(%d, %d) should equal %d", a, b, expected));
    }

    // ─────────── DATA PROVIDER WITH STRINGS ───────────

    @DataProvider(name = "palindromeData")
    public Object[][] palindromeDataProvider() {
        return new Object[][]{
                {"racecar", true},
                {"madam", true},
                {"hello", false},
                {"A man a plan a canal Panama", true},
                {"Was it a car or a cat I saw", true},
                {"Not a palindrome", false}
        };
    }

    @Test(dataProvider = "palindromeData", groups = "lesson03")
    public void testPalindrome(String text, boolean expected) {
        Assert.assertEquals(calc.isPalindrome(text), expected,
                "isPalindrome(\"" + text + "\") should be " + expected);
    }

    // ─────────── DATA PROVIDER FOR FACTORIAL ───────────

    @DataProvider(name = "factorialData")
    public Object[][] factorialData() {
        return new Object[][]{
                {0, 1},    // 0! = 1
                {1, 1},    // 1! = 1
                {5, 120},  // 5! = 120
                {10, 3628800}
        };
    }

    @Test(dataProvider = "factorialData", groups = "lesson03")
    public void testFactorial(int input, int expected) {
        Assert.assertEquals(calc.factorial(input), expected);
    }

    // ─────────── @Parameters from testng.xml ───────────
    // Values come from <parameter name="browser" value="chrome"/> in testng.xml.
    // Useful for environment-level config (browser type, base URL, etc.)
    //
    // JUnit 4: no direct equivalent
    // JUnit 5: no direct equivalent (use system properties or extensions)

    @Test(groups = "lesson03")
    @Parameters({"browser"})
    public void testWithXmlParameter(@org.testng.annotations.Optional("chrome") String browser) {
        // @Optional provides a default value if the parameter is not in testng.xml
        System.out.println("Running test with browser: " + browser);
        Assert.assertNotNull(browser);
    }
}

