package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
  protected WebDriver driver;
  protected final String baseUrl = "https://www.saucedemo.com";
  WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  protected WebElement findVisibleElement(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected WebElement findExistElement(By locator) {
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }
}
