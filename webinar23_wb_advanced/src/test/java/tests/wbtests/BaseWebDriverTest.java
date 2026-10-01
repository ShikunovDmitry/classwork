package tests.wbtests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.BaseTest;

import java.time.Duration;

public class BaseWebDriverTest extends BaseTest {
  protected final static String BASE_URL = "https://the-internet.herokuapp.com";
  WebDriverWait wait;
  @BeforeMethod
  public void beforeTests() {
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  @AfterMethod
  public void afterTests() throws InterruptedException {
    Thread.sleep(Duration.ofSeconds(5));
  }
}
