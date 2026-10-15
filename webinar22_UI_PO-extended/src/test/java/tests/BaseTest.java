package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
  protected WebDriver driver;

  @BeforeMethod
  public void setUp() {
    // Automatically downloads the correct ChromeDriver
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless");  // uncomment to run without UI
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    System.out.println("✅ Browser started");
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      System.out.println("✅ Browser closed");
    }
  }
}
