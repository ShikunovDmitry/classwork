package tests.wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.time.Duration;


public class WaitTests extends BaseTest {
  private final String base_url = "https://the-internet.herokuapp.com/dynamic_loading/1";

  @BeforeMethod
  public void setUpWaitTest() {
    driver.get(base_url);
    //  driver.findElement(By.id("start")).findElement(By.tagName("button")).click();
    driver.findElement(By.cssSelector("#start>button")).click();
  }

  @Test
  public void dynamicLoadingElementTest() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
    // Assert.assertTrue(driver.findElement(By.id("finish")).isDisplayed());

    WebElement finishTextWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
    Assert.assertTrue(finishTextWebElement.isDisplayed());
  }

  @Test
  public void fluentWaitTest() {
    Wait<WebDriver> fluentWait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(5))
        .ignoring(NoSuchElementException.class)
        .withMessage("No element found");

    WebElement finishTextWebElement = fluentWait.until(webDriver -> {
      try {
        WebElement element = driver.findElement(By.id("finish"));
        return element.isDisplayed() ? element : null;
      } catch (NoSuchElementException e) {
        return null;
      }
    });
    Assert.assertNotNull(finishTextWebElement, "Element not found");
    Assert.assertTrue(finishTextWebElement.isDisplayed(), "Element not displayed");
  }
}
