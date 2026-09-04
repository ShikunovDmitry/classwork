package it.academy;

import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 9: Listeners — Custom Test Event Handling                         ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  ITestListener allows you to hook into test lifecycle events.              ║
 * ║  Common use cases:                                                         ║
 * ║  • Take a screenshot on failure (Selenium)                                 ║
 * ║  • Custom logging / reporting                                              ║
 * ║  • Retry failed tests                                                      ║
 * ║  • Send notifications                                                      ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ ITestListener│ @Rule TestWatcher│ TestWatcher (extension)       │      ║
 * ║  │ ISuiteList-  │ RunListener      │ TestExecutionListener         │      ║
 * ║  │ ener         │                  │                               │      ║
 * ║  │ IReporter    │                  │                               │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ ⚠ TestNG     │                  │ JUnit 5 uses Extension model  │      ║
 * ║  │ listeners    │                  │ (@ExtendWith). TestNG uses     │      ║
 * ║  │ can be added │                  │ @Listeners annotation or      │      ║
 * ║  │ via XML,     │                  │ testng.xml configuration.     │      ║
 * ║  │ annotation,  │                  │                               │      ║
 * ║  │ or SPI       │                  │                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class CustomTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[LISTENER] Test STARTED: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("[LISTENER] Test PASSED: " + result.getName() + " (" + duration + "ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[LISTENER] Test FAILED: " + result.getName());
        System.out.println("   Reason: " + result.getThrowable().getMessage());
        // In Selenium projects, you would take a screenshot here:
        // takeScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[LISTENER] Test SKIPPED: " + result.getName());
    }
}

