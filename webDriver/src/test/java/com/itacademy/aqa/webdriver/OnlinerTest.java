package com.itacademy.aqa.webdriver;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OnlinerTest extends BaseTest{
    public final static By TV_MENU = By.xpath("//*[contains(text(),'Телевизоры')]");
    public final static By TV_PAGE_LOCATOR = By.xpath("//a[contains(text(),'Телевизор')]");

    @BeforeMethod
    public void driverInitialize() {
        webDriver.get("https://www.onliner.by/");
    }

    @Test
    public void tvMenuTest() {

        WebElement tvMenu = webDriver.findElement(TV_MENU);
        tvMenu.click();

        Wait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(TV_PAGE_LOCATOR));
//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(TV_PAGE_LOCATOR,9));

//        WebElement pageElement = webDriver.findElement(TV_PAGE_LOCATOR);
//        Assert.assertTrue(pageElement.isDisplayed());
        List<WebElement> pageElement = webDriver.findElements(TV_PAGE_LOCATOR);
        Assert.assertTrue(pageElement.size() > 0, "Tv page is not opened");

    }

    @Test
    public void tvMenuFluentTest() {

        WebElement tvMenu = webDriver.findElement(TV_MENU);
        tvMenu.click();

        FluentWait<WebDriver> wait = new FluentWait<>(webDriver);
        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.ignoring(ElementNotVisibleException.class);
        wait.until(webDriver1 -> webDriver1.findElements(TV_PAGE_LOCATOR).size() > 0);
//        wait.until(webDriver1 -> webDriver1.findElements(TV_PAGE_LOCATOR).size() == 0);

//        WebElement pageElement = webDriver.findElement(TV_PAGE_LOCATOR);
//        Assert.assertTrue(pageElement.isDisplayed());
        List<WebElement> pageElement = webDriver.findElements(TV_PAGE_LOCATOR);
        Assert.assertTrue(pageElement.size() > 0, "Tv page is not opened");

    }
}
