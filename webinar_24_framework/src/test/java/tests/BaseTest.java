package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import webdriver.Browser;

public class BaseTest {

  @BeforeMethod
  public void setUp() {
    Browser.getDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    Browser.printBrowserLogs();
    Browser.consoleLogs();
    Browser.close();
  }
}
