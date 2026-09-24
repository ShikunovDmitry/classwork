package it.academy;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry Analyzer — automatically retries failed tests up to a maximum count.
 *
 * This is a UNIQUE TestNG feature with NO direct equivalent in JUnit 4 or 5.
 * JUnit 5 requires a custom extension to achieve similar behavior.
 *
 * Use case: flaky tests in UI automation (e.g., Selenium) that may fail
 * due to timing issues, network problems, etc.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 2;  // retry up to 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY) {
            retryCount++;
            System.out.println("[RETRY] Retrying test: " + result.getName()
                    + " (attempt " + (retryCount + 1) + "/" + (MAX_RETRY + 1) + ")");
            return true;  // true = retry the test
        }
        return false;  // false = don't retry, mark as failed
    }
}

