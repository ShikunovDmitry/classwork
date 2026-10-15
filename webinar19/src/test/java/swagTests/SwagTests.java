package swagTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class SwagTests {
  private WebDriver driver;
  @BeforeEach
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();

    System.out.println("✅ Browser started");
  }

  @Test
  @DisplayName("Test user can login")
  public void loginTests() {}

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      driver = null;
      System.out.println("✅ Browser closed");
    }
  }
}
