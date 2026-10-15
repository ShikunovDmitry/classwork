package it.academy;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 11: Test Inheritance                                              ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG supports test class inheritance:                                   ║
 * ║  • @BeforeMethod/@AfterMethod in parent run for child tests               ║
 * ║  • @Test methods in parent are inherited by child                          ║
 * ║  • Groups are inherited                                                    ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ Full         │ Supports         │ Supports inheritance but     │      ║
 * ║  │ inheritance  │ inheritance.     │ @BeforeAll/@AfterAll must be │      ║
 * ║  │ support.     │ @Before/@After   │ static (tricky with          │      ║
 * ║  │ No static    │ in parent work.  │ inheritance).                 │      ║
 * ║  │ requirements.│                  │                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * This is a base test class. Child test classes inherit setup/teardown.
 * Common pattern in Selenium test automation:
 *   BaseTest → sets up WebDriver
 *   LoginPageTest extends BaseTest → inherits driver setup
 */
public abstract class BaseTest {

    // Simulate a shared resource (e.g., WebDriver in Selenium)
    protected String sharedResource;

    @BeforeMethod
    public void baseSetUp() {
        // In Selenium projects: driver = new ChromeDriver();
        sharedResource = "initialized";
        System.out.println("  [BaseTest] @BeforeMethod: resource initialized");
    }

    @AfterMethod
    public void baseTearDown() {
        // In Selenium projects: driver.quit();
        sharedResource = null;
        System.out.println("  [BaseTest] @AfterMethod: resource cleaned up");
    }
}

