package com.itacademy.aqa;

import com.itacademy.aqa.steam.pages.SteamAboutPage;
import com.itacademy.aqa.steam.pages.SteamMainPage;
import com.itacademy.aqa.webdriver.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SteamTest extends BaseTest {
    public static By NAV_MENU_LOCATOR = By.xpath("//*[contains(@class,'store_nav_bg')]//*[contains(@class,'tab')][not(@style)]");

    @BeforeMethod

    public void navigateToSteam() {
        webDriver.get("https://store.steampowered.com/");
    }
    @Test
    public void testVerifyNumbersOfElementsInMenu(){
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.numberOfElementsToBe(NAV_MENU_LOCATOR, 6));

        List<WebElement> menus = webDriver.findElements(NAV_MENU_LOCATOR);

        Assert.assertEquals(menus.size(), 6, " Number of menu items is not the same as expected");
    }
    @Test
    public void testVerifyNumbersOfElementsInMenuByFluentWait(){
        FluentWait<WebDriver> wait = new FluentWait<>(webDriver);
        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(ElementNotInteractableException.class);

        wait.until(webDriver1 -> webDriver1.findElements(NAV_MENU_LOCATOR).size()>5);

        List<WebElement> menus = webDriver.findElements(NAV_MENU_LOCATOR);

        Assert.assertEquals(menus.size(), 6, " Number of menu items is not the same as expected");
    }

    @Test
    public void testSteamInstallPageCanBeOpened(){
        SteamMainPage steamMainPage = new SteamMainPage();

        Assert.assertTrue(steamMainPage.isOpened(),"Steam page isn't opened");

        SteamAboutPage steamAboutPage = steamMainPage.openInstallmentPage();

//        SteamAboutPage steamAboutPage = new SteamAboutPage(webDriver);

        Assert.assertTrue(steamAboutPage.isOpened(),"About page isn't opened");

    }
}
