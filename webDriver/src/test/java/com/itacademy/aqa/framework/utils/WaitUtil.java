package com.itacademy.aqa.framework.utils;

import com.itacademy.aqa.framework.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
    public static void waitUntilElementVisible(WebDriver driver, By locator, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public static void waitUntilElementVisible(WebElement webElement, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public static void waitForElementsCount(WebDriver webDriver, By menuItemsLocator, int i) {
        //TODO: create waiter
    }
}
