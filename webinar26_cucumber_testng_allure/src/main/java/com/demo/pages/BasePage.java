package com.demo.pages;

import com.demo.utils.AllureUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Base Page Object with common Selenium actions
 * All page objects extend this class
 */
public abstract class BasePage {

  protected final Logger log = LoggerFactory.getLogger(getClass());
  protected final WebDriver driver;
  protected final WebDriverWait wait;
  protected final WebDriverWait shortWait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  // ==================== Navigation ====================

  @Step("Navigate to URL: {url}")
  public void navigateTo(String url) {
    log.info("Navigating to: {}", url);
    driver.get(url);
    AllureUtils.addParameter("URL", url);
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  // ==================== Element Interactions ====================

  @Step("Click element: {locator}")
  protected void click(By locator) {
    log.debug("Clicking element: {}", locator);
    WebElement element = waitForClickable(locator);
    element.click();
  }

  @Step("Type '{text}' into element: {locator}")
  protected void type(By locator, String text) {
    log.debug("Typing '{}' into: {}", text, locator);
    WebElement element = waitForVisible(locator);
    element.clear();
    element.sendKeys(text);
  }

  protected void typeSlowly(By locator, String text) {
    WebElement element = waitForVisible(locator);
    element.clear();
    for (char c : text.toCharArray()) {
      element.sendKeys(String.valueOf(c));
      try { Thread.sleep(50); } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  protected String getText(By locator) {
    return waitForVisible(locator).getText();
  }

  protected String getAttribute(By locator, String attribute) {
    return waitForVisible(locator).getAttribute(attribute);
  }

  protected boolean isDisplayed(By locator) {
    try {
      return shortWait.until(
          ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
  }

  protected boolean isEnabled(By locator) {
    try {
      return driver.findElement(locator).isEnabled();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected void selectByVisibleText(By locator, String text) {
    Select select = new Select(waitForVisible(locator));
    select.selectByVisibleText(text);
  }

  protected void selectByValue(By locator, String value) {
    Select select = new Select(waitForVisible(locator));
    select.selectByValue(value);
  }

  protected List<WebElement> findElements(By locator) {
    return driver.findElements(locator);
  }

  protected int countElements(By locator) {
    return driver.findElements(locator).size();
  }

  // ==================== Wait Methods ====================

  protected WebElement waitForVisible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected WebElement waitForClickable(By locator) {
    return wait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  protected void waitForInvisible(By locator) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  protected void waitForText(By locator, String text) {
    wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
  }

  protected void waitForUrlContains(String urlPart) {
    wait.until(ExpectedConditions.urlContains(urlPart));
  }

  // ==================== JavaScript Actions ====================

  protected void jsClick(By locator) {
    WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  protected void scrollToElement(By locator) {
    WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView(true);", element);
  }

  protected void highlightElement(By locator) {
    WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].style.border='3px solid red'", element);
  }
}