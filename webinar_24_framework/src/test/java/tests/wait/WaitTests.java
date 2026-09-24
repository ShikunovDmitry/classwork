package tests.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webdriver.Browser;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;


public class WaitTests extends BaseTest {
  private final String base_url = "https://the-internet.herokuapp.com/dynamic_loading/1";

  @BeforeMethod
  public void setUpWaitTest() {
    Browser.getDriver().get(base_url);
    //  driver.findElement(By.id("start")).findElement(By.tagName("button")).click();
    Browser.getDriver().findElement(By.cssSelector("#start>button")).click();
  }

  @Test
  public void dynamicLoadingElementTest() {

    By finishTextLocator = By.id("finish");
    WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(30));

    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
    // Assert.assertTrue(driver.findElement(By.id("finish")).isDisplayed());

    WebElement finishTextWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(finishTextLocator));

    // if nothing found by locator it trow an exception
    WebElement webElement = Browser.getDriver().findElement(finishTextLocator);

    // if nothing find, it return empty list
    List<WebElement> webElementList = Browser.getDriver().findElements(finishTextLocator);

    if (webElementList.size() > 0) {
      System.out.println("Elements found");
    } else {
      System.out.println(webElementList.size() + "size will be = 0");
      System.out.println("Elements not found");
    }

    Assert.assertTrue(finishTextWebElement.isDisplayed());
  }

  @Test
  public void fluentWaitTest() {
    Wait<WebDriver> fluentWait = new FluentWait<>(Browser.getDriver())
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(5))
        .ignoring(NoSuchElementException.class)
        .withMessage("No element found");

    WebElement finishTextWebElement = fluentWait.until(webDriver -> {
      try {
        WebElement element = Browser.getDriver().findElement(By.id("finish"));
        return element.isDisplayed() ? element : null;
      } catch (NoSuchElementException e) {
        return null;
      }
    });
    Assert.assertNotNull(finishTextWebElement, "Element not found");
    Assert.assertTrue(finishTextWebElement.isDisplayed(), "Element not displayed");
  }
}
