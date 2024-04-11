package com.itacademy.aqa.tests;

import com.itacademy.aqa.utils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class OnlinerTest extends BaseTest {
    public final static By TV_MENU = By.xpath("//*[contains(text(),'Телевизоры')]");
    public final static By TV_PAGE_LOCATOR = By.xpath("//a[contains(text(),'Телевизор')]");

    @BeforeMethod
    public void pageLoad() {
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

    @Test
    public void testCheckBox() {
        webDriver.get("https://catalog.onliner.by/tv");
        webDriver.findElement(By.xpath("//*[@class='popover-style__container']//*[contains(text(), ' Да, верно')]")).click();
        By lgButton = By.xpath("//div[@class='catalog-form__checkbox-sign'][text()='LG']/../../*[contains(@class,'i-checkbox__faux')]");
        WaitUtil.waitUntilElementVisible(webDriver, lgButton, 30);
        WebElement webElement = webDriver.findElement(lgButton);
        webElement.click();

    }

    @Test
    public void scollByJsExecutor() {
        webDriver.get("https://catalog.onliner.by/tv");
        webDriver.findElement(By.xpath("//*[@class='popover-style__container']//*[contains(text(), ' Да, верно')]")).click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        By kiviCheckboxLocator = By.xpath("//div[@class='catalog-form__checkbox-sign'][text()='KIVI']/../../*[contains(@class,'i-checkbox__faux')]");
        WebElement kiviCheckbox = webDriver.findElement(kiviCheckboxLocator);
//        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", kiviCheckbox);
//        int verticalOffset = kiviCheckbox.getLocation().getY()/2;
        int verticalOffset = kiviCheckbox.getLocation().getY() - 30;
        jsExecutor.executeScript("window.scrollBy(0," + verticalOffset + ");");
        kiviCheckbox.click();
    }

    @Test
    public void scollByAction() {
        webDriver.get("https://catalog.onliner.by/tv");
        webDriver.findElement(By.xpath("//*[@class='popover-style__container']//*[contains(text(), ' Да, верно')]")).click();
        By kiviCheckboxLocator = By.xpath("//div[@class='catalog-form__checkbox-sign'][text()='KIVI']/../../*[contains(@class,'i-checkbox__faux')]");
        WebElement kiviCheckbox = webDriver.findElement(kiviCheckboxLocator);
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.PAGE_DOWN)
                .build()
                .perform();
        kiviCheckbox.click();
    }


    @Test
    public void testSearch() {
        WebElement searchFiels = webDriver.findElement(By.xpath("//input[@class='fast-search__input']"));
        searchFiels.sendKeys("Телевизоры");
        By tvsResult = By.xpath("//a[contains(text(),'Телевизоры')]");
        WaitUtil.waitUntilElementVisible(webDriver, tvsResult, 30);
        webDriver.findElement(tvsResult).click();
    }
}
